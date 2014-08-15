/*
 * Created on Mar 6, 2005
 * 
 * (c)2005 RKSoft, Meerbusch, Germany.  All rights reserved.
 */

package sdljavax.guichan.widgets;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import sdljavax.guichan.GUIException;
import sdljavax.guichan.evt.Key;
import sdljavax.guichan.evt.KeyListener;
import sdljavax.guichan.evt.MouseInput;
import sdljavax.guichan.evt.MouseListener;
import sdljavax.guichan.gfx.Graphics;
import sdljavax.guichan.gfx.Rectangle;


/**
 * This is a TextBox. It is used for displaying text. A TextBox can also be used for editing of
 * text.
 * 
 * NOTE: A plain TextBox is really ugly and looks much better inside a ScrollArea.
 * 
 * @author Rainer Koschnick <a href="mailto:arkay@gmx.net"><arkay@gmx.net></a>
 */
public class TextBox extends Widget implements MouseListener, KeyListener {
	protected boolean	m_bEditable		= true;
	protected boolean	m_bOpaque		= true;
	protected List		m_listTextRows	= new ArrayList();
	protected int		m_nCaretColumn	= 0;
	protected int		m_nCaretRow		= 0;

	/**
	 * Constructor.
	 * @throws GUIException 
	 */
	public TextBox() throws GUIException {
		this(null);
	}

	/**
	 * Constructor.
	 * 
	 * @param text
	 *            the text of the TextBox.
	 * @throws GUIException 
	 */
	public TextBox(String text) throws GUIException {
		super();

		if (null != text) {
			setText(text);
		} else {
			setText("");
		}

		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
		adjustSize();
		setBorderSize(1);
	}

	/**
	 * Adds a text row to the text.
	 * 
	 * @param row
	 *            a row.
	 */
	public void addRow(String row) {
		m_listTextRows.add(row);
		adjustSize();
	}

	/**
	 * Adjusts the TextBox size to fit the font size.
	 */
	protected void adjustSize() {
		int width = 0;
		for (int i = 0; i < m_listTextRows.size(); i++) {
			int w = getFont().getWidth((String) m_listTextRows.get(i));
			if (width < w) {
				width = w;
			}
		}

		setWidth(width + 1);
		setHeight(getFont().getHeight() * m_listTextRows.size());
	}

	/**
	 * Add a String value at the end of a line
	 * 
	 * @param line
	 *            line number
	 * @param value
	 *            String value
	 */
	protected void bufferAppendStringAt(int line, String value) {
		StringBuffer buf = new StringBuffer((String) m_listTextRows.get(line));
		buf.append(value);
		m_listTextRows.set(line, buf.toString());
	}

	/**
	 * Insert a character at a given position
	 * 
	 * @param line
	 *            line number
	 * @param column
	 *            column number
	 * @param value
	 *            character value
	 */
	protected void bufferInsertCharAt(int line, int column, char value) {
		StringBuffer buf = new StringBuffer((String) m_listTextRows.get(line));
		buf.insert(column, value);
		m_listTextRows.set(line, buf.toString());
	}

	/**
	 * Insert a String at a line
	 * 
	 * @param line
	 *            line number
	 * @param string
	 *            string to insert
	 */
	protected void bufferInsertLineAt(int line, String string) {
		m_listTextRows.add(line, string);
	}

	/**
	 * Insert a line at a given position
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            line number
	 * @param value
	 *            String value
	 */
	protected void bufferInsertStringAt(int x, int y, String value) {
		StringBuffer buf = new StringBuffer((String) m_listTextRows.get(x));
		buf.insert(y, value);
		m_listTextRows.set(x, buf.toString());
	}

	/**
	 * Remove a character at a given position
	 * 
	 * @param line
	 *            line number
	 * @param column
	 *            column number
	 */
	protected void bufferRemoveCharAt(int line, int column) {
		StringBuffer buf = new StringBuffer((String) m_listTextRows.get(line));
		buf.deleteCharAt(column);
		m_listTextRows.set(line, buf.toString());
	}

	/**
	 * Remove a line
	 * 
	 * @param line
	 *            line number
	 */
	protected void bufferRemoveLineAt(int line) {
		m_listTextRows.remove(line);
	}

	/**
	 * Set a line to a given length
	 * 
	 * @param line
	 *            line number
	 * @param length
	 *            new length
	 */
	protected void bufferSetSizeAt(int line, int length) {
		StringBuffer buf = new StringBuffer((String) m_listTextRows.get(line));
		buf.setLength(length);
		m_listTextRows.set(line, buf.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#draw(sdljavax.guichan.gfx.Graphics)
	 */
	public void draw(Graphics graphics) throws GUIException {
		if (m_bOpaque) {
			graphics.setColor(getBackgroundColor());
			graphics.fillRectangle(new Rectangle(0, 0, getWidth(), getHeight()));
		}

		if (hasFocus() && isEditable()) {
			drawCaret(graphics, getFont().getWidth(
				((String) m_listTextRows.get(m_nCaretRow)).substring(0, m_nCaretColumn)), m_nCaretRow
				* getFont().getHeight());
		}

		graphics.setColor(getForegroundColor());
		graphics.setFont(getFont());

		int nFontHeight = getFont().getHeight();

		for (int i = 0; i < m_listTextRows.size(); i++) {
			// Move the text one pixel so we can have a caret before a letter.
			graphics.drawText((String) m_listTextRows.get(i), 1, i * nFontHeight);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#drawBorder(sdljavax.guichan.gfx.Graphics)
	 */
	public void drawBorder(Graphics graphics) throws GUIException {
		int width = getWidth() + getBorderSize() * 2 - 1;
		int height = getHeight() + getBorderSize() * 2 - 1;

		graphics.setColor(getBackgroundColor());

		for (int i = 0; i < getBorderSize(); ++i) {
			graphics.drawLine(i, i, width - i, i);
			graphics.drawLine(i, i, i, height - i);
			graphics.drawLine(width - i, i, width - i, height - i);
			graphics.drawLine(i, height - i, width - i, height - i);
		}
	}

	/**
	 * Draws the caret.
	 * 
	 * @param graphics
	 *            a Graphics object.
	 * @param x
	 *            the x position.
	 * @param y
	 *            the y position.
	 */
	protected void drawCaret(Graphics graphics, int x, int y) throws GUIException {
		graphics.setColor(getForegroundColor());
		graphics.drawLine(x, getFont().getHeight() + y, x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.widgets.Widget#fontChanged()
	 */
	public void fontChanged() {
		adjustSize();
		super.fontChanged();
	}

	/**
	 * @return the column the caret is in in the text.
	 */
	public int getCaretColumn() {
		return m_nCaretColumn;
	}

	/**
	 * @return the caret position in the text.
	 */
	public int getCaretPosition() {
		int pos = 0;
		for (int row = 0; row < m_nCaretRow; row++) {
			pos += ((String) m_listTextRows.get(row)).length();
		}
		return pos + m_nCaretColumn;
	}

	/**
	 * @return the row the caret is in in the text.
	 */
	public int getCaretRow() {
		return m_nCaretRow;
	}

	/**
	 * @return the number of rows in the TextBox.
	 */
	public int getNumberOfRows() {
		return m_listTextRows.size();
	}

	/**
	 * @return the text of the TextBox.
	 */
	public String getText() {
		if (m_listTextRows.size() == 0) {
			return "";
		}
		StringBuffer bufText = new StringBuffer();
		int nLines = m_listTextRows.size();
		for (int i = 0; i < nLines; ++i) {
			bufText.append(m_listTextRows.get(i));
			if (i < nLines - 1) {
				bufText.append("\n");
			}
		}
		return bufText.toString();
	}

	/**
	 * @return the text of a certain row in the TextBox.
	 */
	public final String getTextRow(int row) {
		return (String) m_listTextRows.get(row);
	}

	/**
	 * @return true it the TextBox is editable.
	 */
	public boolean isEditable() {
		return m_bEditable;
	}

	/**
	 * Checks if the TextBox is opaque
	 * 
	 * @return true if the TextBox is opaque
	 */
	public boolean isOpaque() {
		return m_bOpaque;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyPress(sdljavax.guichan.evt.Key)
	 */
	public void keyPress(Key key) throws GUIException {
		if (key.getValue() == Key.LEFT) {
			--m_nCaretColumn;
			if (m_nCaretColumn < 0) {
				--m_nCaretRow;

				if (m_nCaretRow < 0) {
					m_nCaretRow = 0;
					m_nCaretColumn = 0;
				} else {
					m_nCaretColumn = ((String) m_listTextRows.get(m_nCaretRow)).length();
				}
			}
		} else if (key.getValue() == Key.RIGHT) {
			++m_nCaretColumn;
			if (m_nCaretColumn > ((String) m_listTextRows.get(m_nCaretRow)).length()) {
				++m_nCaretRow;

				if (m_nCaretRow >= m_listTextRows.size()) {
					m_nCaretRow = m_listTextRows.size() - 1;
					if (m_nCaretRow < 0) {
						m_nCaretRow = 0;
					}

					m_nCaretColumn = ((String) m_listTextRows.get(m_nCaretRow)).length();
				} else {
					m_nCaretColumn = 0;
				}
			}
		} else if (key.getValue() == Key.DOWN) {
			setCaretRow(m_nCaretRow + 1);
		} else if (key.getValue() == Key.UP) {
			setCaretRow(m_nCaretRow - 1);
		} else if (key.getValue() == Key.HOME) {
			m_nCaretColumn = 0;
		} else if (key.getValue() == Key.END) {
			m_nCaretColumn = ((String) m_listTextRows.get(m_nCaretRow)).length();
		} else if (key.getValue() == Key.ENTER && m_bEditable) {
			String strLine = (String) m_listTextRows.get(m_nCaretRow);
			String strNextLine = strLine.substring(m_nCaretColumn, strLine.length());
			bufferInsertLineAt(m_nCaretRow + 1, strNextLine);
			bufferSetSizeAt(m_nCaretRow, m_nCaretColumn);
			++m_nCaretRow;
			m_nCaretColumn = 0;
		} else if (key.getValue() == Key.BACKSPACE && m_nCaretColumn != 0 && m_bEditable) {
			bufferRemoveCharAt(m_nCaretRow, m_nCaretColumn - 1);
			--m_nCaretColumn;
		} else if (key.getValue() == Key.BACKSPACE && m_nCaretColumn == 0 && m_nCaretRow != 0 && m_bEditable) {
			String strLine = (String) m_listTextRows.get(m_nCaretRow - 1);
			m_nCaretColumn = strLine.length();
			bufferInsertStringAt(m_nCaretRow - 1, strLine.length(), (String) m_listTextRows.get(m_nCaretRow));
			bufferRemoveLineAt(m_nCaretRow);
			--m_nCaretRow;
		} else if (key.getValue() == Key.DELETE
			&& m_nCaretColumn < ((String) m_listTextRows.get(m_nCaretRow)).length()
			&& m_bEditable) {
			bufferRemoveCharAt(m_nCaretRow, m_nCaretColumn);
		} else if (key.getValue() == Key.DELETE
			&& m_nCaretColumn == ((String) m_listTextRows.get(m_nCaretRow)).length()
			&& m_nCaretRow < (m_listTextRows.size() - 1)
			&& m_bEditable) {
			bufferAppendStringAt(m_nCaretRow, (String) m_listTextRows.get(m_nCaretRow + 1));
			bufferRemoveLineAt(m_nCaretRow + 1);
		} else if (key.getValue() == Key.PAGE_UP) {
			int rowsPerPage;
			Dimension size = getParent().getDrawSize(this);
			rowsPerPage = size.height / getFont().getHeight();
			m_nCaretRow -= rowsPerPage;

			if (m_nCaretRow < 0) {
				m_nCaretRow = 0;
			}
		} else if (key.getValue() == Key.PAGE_DOWN) {
			int rowsPerPage;
			Dimension size = getParent().getDrawSize(this);
			rowsPerPage = size.height / getFont().getHeight();
			m_nCaretRow += rowsPerPage;

			if (m_nCaretRow >= m_listTextRows.size()) {
				m_nCaretRow = m_listTextRows.size() - 1;
			}
		} else if (key.getValue() == Key.TAB && m_bEditable) {
			bufferInsertStringAt(m_nCaretRow, m_nCaretColumn, "    ");
			m_nCaretColumn += 4;
		} else if (key.isCharacter() && m_bEditable) {
			bufferInsertCharAt(m_nCaretRow, m_nCaretColumn, (char) key.getValue());
			++m_nCaretColumn;
		}

		adjustSize();
		scrollToCaret();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.KeyListener#keyRelease(sdljavax.guichan.evt.Key)
	 */
	public void keyRelease(Key key) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseClick(int, int, int, int)
	 */
	public void mouseClick(int x, int y, int button, int count) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseIn()
	 */
	public void mouseIn() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseMotion(int, int)
	 */
	public void mouseMotion(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseOut()
	 */
	public void mouseOut() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mousePress(int, int, int)
	 */
	public void mousePress(int x, int y, int button) throws GUIException {
		if (hasMouse() && MouseInput.LEFT == button) {
			m_nCaretRow = y / getFont().getHeight();
			if (m_nCaretRow >= m_listTextRows.size()) {
				m_nCaretRow = m_listTextRows.size() - 1;
			}
			m_nCaretColumn = getFont().getStringIndexAt((String) m_listTextRows.get(m_nCaretRow), x);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseRelease(int, int, int)
	 */
	public void mouseRelease(int x, int y, int button) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelDown(int, int)
	 */
	public void mouseWheelDown(int x, int y) throws GUIException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sdljavax.guichan.evt.MouseListener#mouseWheelUp(int, int)
	 */
	public void mouseWheelUp(int x, int y) throws GUIException {}

	/**
	 * Scrolls the TextBox to the caret if the TextBox is in a ScrollArea.
	 */
	public void scrollToCaret() throws GUIException {
		Widget par = getParent();
		if (null == par) {
			return;
		}

		ScrollArea scrollArea = (ScrollArea) par;
		if (scrollArea != null) {
			Rectangle scroll = new Rectangle();
			scroll.x = getFont().getWidth(((String) m_listTextRows.get(m_nCaretRow)).substring(0, m_nCaretColumn));
			scroll.y = getFont().getHeight() * m_nCaretRow;
			scroll.width = 6;
			scroll.height = getFont().getHeight() + 2;
			scrollArea.scrollToRectangle(scroll);
		}
	}

	/**
	 * Sets the column the caret should be in in the text.
	 * 
	 * @param column
	 *            the column number.
	 */
	public void setCaretColumn(int column) {
		m_nCaretColumn = column;

		int nRowLength = ((String) m_listTextRows.get(m_nCaretRow)).length();

		if (m_nCaretColumn > nRowLength) {
			m_nCaretColumn = nRowLength;
		}

		if (m_nCaretColumn < 0) {
			m_nCaretColumn = 0;
		}
	}

	/**
	 * Sets the position of the caret in the text.
	 * 
	 * @param position
	 *            the positon of the caret.
	 */
	public void setCaretPosition(int position) {
		int row;

		for (row = 0; row < m_listTextRows.size(); row++) {
			if (position <= ((String) m_listTextRows.get(row)).length()) {
				m_nCaretRow = row;
				m_nCaretColumn = position;
				return; // we are done
			}
			position--;
		}

		// position beyond end of text
		m_nCaretRow = m_listTextRows.size() - 1;
		m_nCaretColumn = ((String) m_listTextRows.get(m_nCaretRow)).length();
	}

	/**
	 * Sets the row the caret should be in in the text.
	 * 
	 * @param row
	 *            the row number.
	 */
	public void setCaretRow(int row) {
		m_nCaretRow = row;

		if (m_nCaretRow >= m_listTextRows.size()) {
			m_nCaretRow = m_listTextRows.size() - 1;
		}

		if (m_nCaretRow < 0) {
			m_nCaretRow = 0;
		}

		setCaretColumn(m_nCaretColumn);
	}

	/**
	 * Sets the row and the column the caret should be in in the text.
	 * 
	 * @param row
	 *            the row number.
	 * @param column
	 *            the column number.
	 */
	public void setCaretRowColumn(int row, int column) {
		setCaretRow(row);
		setCaretColumn(column);
	}

	/**
	 * Sets if the TextBox should be editable or not.
	 * 
	 * @param editable
	 *            true if the TextBox should be editable.
	 */
	public void setEditable(boolean editable) {
		m_bEditable = editable;
	}

	/**
	 * Sets the TextBox to be opaque.
	 * 
	 * @param opaque
	 *            true if the TextBox should be opaque.
	 */
	public void setOpaque(boolean opaque) {
		m_bOpaque = opaque;
	}

	/**
	 * Sets the text of the TextBox.
	 * 
	 * @param text
	 *            the text of the TextBox.
	 */
	public void setText(String text) {
		m_nCaretColumn = 0;
		m_nCaretRow = 0;
		m_listTextRows.clear();

		int pos, lastPos = 0;
		int length;
		do {
			pos = text.indexOf('\n', lastPos);

			if (-1 != pos) {
				length = pos - lastPos;
			} else {
				length = text.length() - lastPos;
			}
			String sub = text.substring(lastPos, lastPos + length);
			m_listTextRows.add(sub);
			lastPos = pos + 1;

		} while (-1 != pos);

		adjustSize();
	}

	/**
	 * Sets the text of a certain row in a TextBox.
	 * 
	 * @param row
	 *            the row number.
	 * @param text
	 *            the text of a certain row in the TextBox.
	 */
	public void setTextRow(int row, String text) {
		m_listTextRows.set(row, text);
		if (row == m_nCaretRow) {
			setCaretColumn(m_nCaretColumn);
		}
		adjustSize();
	}
}
