%module SWIG_SDLTTF
%{
  #include "SDL_ttf.h"

  SDL_version SWIG_TTF_VERSION() {
    SDL_version version;

    TTF_VERSION(&version);

    return version;
  }

  SDL_Color color;
  SDL_Color back;

  SDL_Surface * TTF_RenderText_Solid_FAST(TTF_Font * font, const char* text, Uint8 r, Uint8 g, Uint8 b) {
    color.r = r;
    color.g = g;
    color.b = b;

    return TTF_RenderUTF8_Solid(font, text, color);
  }

  SDL_Surface * TTF_RenderText_Shaded_FAST(TTF_Font *font, const char *text, Uint8 fr, Uint8 fg, Uint8 fb, Uint8 br, Uint8 bg, Uint8 bb) {
    color.r = fr;
    color.g = fg;
    color.b = fb;

    back.r = br;
    back.g = bg;
    back.b = bb;

    return TTF_RenderUTF8_Shaded(font, text, color, back);
    
  }

  SDL_Surface * TTF_RenderText_Blended_FAST(TTF_Font *font, const char* text, Uint8 r, Uint8 g, Uint8 b) {
    color.r = r;
    color.g = g;
    color.b = b;

    return TTF_RenderUTF8_Blended(font, text, color);
  }
%}

%javaconst(1);
%import "SDL_version.h"
%import "SDL_types.h"
%import "SDLVideo.i"
%include typemaps.i

%pragma(java) jniclasscode=%{
  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null) {
        System.loadLibrary("sdljava_ttf");
      }
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }
%}


#define TTF_STYLE_NORMAL        0x00
#define TTF_STYLE_BOLD          0x01
#define TTF_STYLE_ITALIC        0x02
#define TTF_STYLE_UNDERLINE     0x04

typedef struct _TTF_Font TTF_Font;

extern int TTF_Init(void);
extern TTF_Font * TTF_OpenFont(const char *file, int ptsize);
extern TTF_Font * TTF_OpenFontIndex(const char *file, int ptsize, long index);
//extern TTF_Font * TTF_OpenFontRW(SDL_RWops *src, int freesrc,
//int ptsize);
//extern TTF_Font * TTF_OpenFontIndexRW(SDL_RWops *src, int free
//src, int ptsize, long index);

extern int TTF_GetFontStyle(TTF_Font *font);
extern void TTF_SetFontStyle(TTF_Font *font, int style);

extern int TTF_FontHeight(TTF_Font *font);

extern int TTF_FontAscent(TTF_Font *font);

extern int TTF_FontDescent(TTF_Font *font);

extern int TTF_FontLineSkip(TTF_Font *font);

extern long TTF_FontFaces(TTF_Font *font);

extern int TTF_FontFaceIsFixedWidth(TTF_Font *font);
extern char * TTF_FontFaceFamilyName(TTF_Font *font);
extern char * TTF_FontFaceStyleName(TTF_Font *font);

extern int TTF_GlyphMetrics(TTF_Font *font, Uint16 ch,
                                     int *OUTPUT, int *OUTPUT,
                                     int *OUTPUT, int *OUTPUT, int *OUTPUT);

extern int TTF_SizeText(TTF_Font *font, const char *text, int *OUTPUT, int *OUTPUT);
extern int TTF_SizeUTF8(TTF_Font *font, const char *text, int *OUTPUT, int *OUTPUT);
extern int TTF_SizeUNICODE(TTF_Font *font, const Uint16 *text, int *OUTPUT, int *OUTPUT);

extern SDL_Surface * TTF_RenderText_Solid_FAST(TTF_Font * font, const char* text, Uint8 r, Uint8 g, Uint8 b);
extern SDL_Surface * TTF_RenderUTF8_Solid(TTF_Font *font,
                                const char *text, SDL_Color fg);
extern SDL_Surface * TTF_RenderUNICODE_Solid(TTF_Font *font,
                                const Uint16 *text, SDL_Color fg);

extern SDL_Surface * TTF_RenderText_Shaded_FAST(TTF_Font *font, const char *text, Uint8 fr, Uint8 fg, Uint8 fb, Uint8 br, Uint8 bg, Uint8 bb);
extern SDL_Surface * TTF_RenderUTF8_Shaded(TTF_Font *font,
                              const char *text, SDL_Color fg, SDL_Color bg);
extern SDL_Surface * TTF_RenderUNICODE_Shaded(TTF_Font *font,
                                const Uint16 *text, SDL_Color fg, SDL_Color bg)
;

extern SDL_Surface * TTF_RenderGlyph_Shaded(TTF_Font *font,
                                Uint16 ch, SDL_Color fg, SDL_Color bg);

extern SDL_Surface * TTF_RenderText_Blended_FAST(TTF_Font *font, const char* text, Uint8 r, Uint8 g, Uint8 b);

extern SDL_Surface * TTF_RenderUTF8_Blended(TTF_Font *font,
                                const char *text, SDL_Color fg);
extern SDL_Surface * TTF_RenderUNICODE_Blended(TTF_Font *font,
                                const Uint16 *text, SDL_Color fg);

extern SDL_Surface * TTF_RenderGlyph_Blended(TTF_Font *font,
                                                Uint16 ch, SDL_Color fg);

extern void TTF_CloseFont(TTF_Font *font);

extern void TTF_Quit(void);

extern SDL_version SWIG_TTF_VERSION();
