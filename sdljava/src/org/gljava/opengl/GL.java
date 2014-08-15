package org.gljava.opengl;
/**
 *  sdljava - a java binding to the SDL API
 *
 *  Copyright (C) 2004  Ivan Z. Ganza
 * 
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA
 *
 *  Ivan Z. Ganza (ivan_ganza@yahoo.com)
 */
import java.nio.*;

import org.gljava.opengl.glu.GLUnurbs;
import org.gljava.opengl.glu.GLUtesselator;
import org.gljava.opengl.glu.GLUquadric;

public interface GL {

    ////////////////////////////////////////////////////////////////////////////////
    // OPENGL 1.0 / 1.1 Constants
    public final static int GL_FALSE = 0x0;
    public final static int GL_TRUE = 0x1;
    public final static int GL_BYTE = 0x1400;
    public final static int GL_UNSIGNED_BYTE = 0x1401;
    public final static int GL_SHORT = 0x1402;
    public final static int GL_UNSIGNED_SHORT = 0x1403;
    public final static int GL_INT = 0x1404;
    public final static int GL_UNSIGNED_INT = 0x1405;
    public final static int GL_FLOAT = 0x1406;
    public final static int GL_DOUBLE = 0x140A;
    public final static int GL_2_BYTES = 0x1407;
    public final static int GL_3_BYTES = 0x1408;
    public final static int GL_4_BYTES = 0x1409;
    public final static int GL_POINTS = 0x0000;
    public final static int GL_LINES = 0x0001;
    public final static int GL_LINE_LOOP = 0x0002;
    public final static int GL_LINE_STRIP = 0x0003;
    public final static int GL_TRIANGLES = 0x0004;
    public final static int GL_TRIANGLE_STRIP = 0x0005;
    public final static int GL_TRIANGLE_FAN = 0x0006;
    public final static int GL_QUADS = 0x0007;
    public final static int GL_QUAD_STRIP = 0x0008;
    public final static int GL_POLYGON = 0x0009;
    public final static int GL_VERTEX_ARRAY = 0x8074;
    public final static int GL_NORMAL_ARRAY = 0x8075;
    public final static int GL_COLOR_ARRAY = 0x8076;
    public final static int GL_INDEX_ARRAY = 0x8077;
    public final static int GL_TEXTURE_COORD_ARRAY = 0x8078;
    public final static int GL_EDGE_FLAG_ARRAY = 0x8079;
    public final static int GL_VERTEX_ARRAY_SIZE = 0x807A;
    public final static int GL_VERTEX_ARRAY_TYPE = 0x807B;
    public final static int GL_VERTEX_ARRAY_STRIDE = 0x807C;
    public final static int GL_NORMAL_ARRAY_TYPE = 0x807E;
    public final static int GL_NORMAL_ARRAY_STRIDE = 0x807F;
    public final static int GL_COLOR_ARRAY_SIZE = 0x8081;
    public final static int GL_COLOR_ARRAY_TYPE = 0x8082;
    public final static int GL_COLOR_ARRAY_STRIDE = 0x8083;
    public final static int GL_INDEX_ARRAY_TYPE = 0x8085;
    public final static int GL_INDEX_ARRAY_STRIDE = 0x8086;
    public final static int GL_TEXTURE_COORD_ARRAY_SIZE = 0x8088;
    public final static int GL_TEXTURE_COORD_ARRAY_TYPE = 0x8089;
    public final static int GL_TEXTURE_COORD_ARRAY_STRIDE = 0x808A;
    public final static int GL_EDGE_FLAG_ARRAY_STRIDE = 0x808C;
    public final static int GL_VERTEX_ARRAY_POINTER = 0x808E;
    public final static int GL_NORMAL_ARRAY_POINTER = 0x808F;
    public final static int GL_COLOR_ARRAY_POINTER = 0x8090;
    public final static int GL_INDEX_ARRAY_POINTER = 0x8091;
    public final static int GL_TEXTURE_COORD_ARRAY_POINTER = 0x8092;
    public final static int GL_EDGE_FLAG_ARRAY_POINTER = 0x8093;
    public final static int GL_V2F = 0x2A20;
    public final static int GL_V3F = 0x2A21;
    public final static int GL_C4UB_V2F = 0x2A22;
    public final static int GL_C4UB_V3F = 0x2A23;
    public final static int GL_C3F_V3F = 0x2A24;
    public final static int GL_N3F_V3F = 0x2A25;
    public final static int GL_C4F_N3F_V3F = 0x2A26;
    public final static int GL_T2F_V3F = 0x2A27;
    public final static int GL_T4F_V4F = 0x2A28;
    public final static int GL_T2F_C4UB_V3F = 0x2A29;
    public final static int GL_T2F_C3F_V3F = 0x2A2A;
    public final static int GL_T2F_N3F_V3F = 0x2A2B;
    public final static int GL_T2F_C4F_N3F_V3F = 0x2A2C;
    public final static int GL_T4F_C4F_N3F_V4F = 0x2A2D;
    public final static int GL_MATRIX_MODE = 0x0BA0;
    public final static int GL_MODELVIEW = 0x1700;
    public final static int GL_PROJECTION = 0x1701;
    public final static int GL_TEXTURE = 0x1702;
    public final static int GL_POINT_SMOOTH = 0x0B10;
    public final static int GL_POINT_SIZE = 0x0B11;
    public final static int GL_POINT_SIZE_GRANULARITY = 0x0B13;
    public final static int GL_POINT_SIZE_RANGE = 0x0B12;
    public final static int GL_LINE_SMOOTH = 0x0B20;
    public final static int GL_LINE_STIPPLE = 0x0B24;
    public final static int GL_LINE_STIPPLE_PATTERN = 0x0B25;
    public final static int GL_LINE_STIPPLE_REPEAT = 0x0B26;
    public final static int GL_LINE_WIDTH = 0x0B21;
    public final static int GL_LINE_WIDTH_GRANULARITY = 0x0B23;
    public final static int GL_LINE_WIDTH_RANGE = 0x0B22;
    public final static int GL_POINT = 0x1B00;
    public final static int GL_LINE = 0x1B01;
    public final static int GL_FILL = 0x1B02;
    public final static int GL_CW = 0x0900;
    public final static int GL_CCW = 0x0901;
    public final static int GL_FRONT = 0x0404;
    public final static int GL_BACK = 0x0405;
    public final static int GL_POLYGON_MODE = 0x0B40;
    public final static int GL_POLYGON_SMOOTH = 0x0B41;
    public final static int GL_POLYGON_STIPPLE = 0x0B42;
    public final static int GL_EDGE_FLAG = 0x0B43;
    public final static int GL_CULL_FACE = 0x0B44;
    public final static int GL_CULL_FACE_MODE = 0x0B45;
    public final static int GL_FRONT_FACE = 0x0B46;
    public final static int GL_POLYGON_OFFSET_FACTOR = 0x8038;
    public final static int GL_POLYGON_OFFSET_UNITS = 0x2A00;
    public final static int GL_POLYGON_OFFSET_POINT = 0x2A01;
    public final static int GL_POLYGON_OFFSET_LINE = 0x2A02;
    public final static int GL_POLYGON_OFFSET_FILL = 0x8037;
    public final static int GL_COMPILE = 0x1300;
    public final static int GL_COMPILE_AND_EXECUTE = 0x1301;
    public final static int GL_LIST_BASE = 0x0B32;
    public final static int GL_LIST_INDEX = 0x0B33;
    public final static int GL_LIST_MODE = 0x0B30;
    public final static int GL_NEVER = 0x0200;
    public final static int GL_LESS = 0x0201;
    public final static int GL_EQUAL = 0x0202;
    public final static int GL_LEQUAL = 0x0203;
    public final static int GL_GREATER = 0x0204;
    public final static int GL_NOTEQUAL = 0x0205;
    public final static int GL_GEQUAL = 0x0206;
    public final static int GL_ALWAYS = 0x0207;
    public final static int GL_DEPTH_TEST = 0x0B71;
    public final static int GL_DEPTH_BITS = 0x0D56;
    public final static int GL_DEPTH_CLEAR_VALUE = 0x0B73;
    public final static int GL_DEPTH_FUNC = 0x0B74;
    public final static int GL_DEPTH_RANGE = 0x0B70;
    public final static int GL_DEPTH_WRITEMASK = 0x0B72;
    public final static int GL_DEPTH_COMPONENT = 0x1902;
    public final static int GL_LIGHTING = 0x0B50;
    public final static int GL_LIGHT0 = 0x4000;
    public final static int GL_LIGHT1 = 0x4001;
    public final static int GL_LIGHT2 = 0x4002;
    public final static int GL_LIGHT3 = 0x4003;
    public final static int GL_LIGHT4 = 0x4004;
    public final static int GL_LIGHT5 = 0x4005;
    public final static int GL_LIGHT6 = 0x4006;
    public final static int GL_LIGHT7 = 0x4007;
    public final static int GL_SPOT_EXPONENT = 0x1205;
    public final static int GL_SPOT_CUTOFF = 0x1206;
    public final static int GL_CONSTANT_ATTENUATION = 0x1207;
    public final static int GL_LINEAR_ATTENUATION = 0x1208;
    public final static int GL_QUADRATIC_ATTENUATION = 0x1209;
    public final static int GL_AMBIENT = 0x1200;
    public final static int GL_DIFFUSE = 0x1201;
    public final static int GL_SPECULAR = 0x1202;
    public final static int GL_SHININESS = 0x1601;
    public final static int GL_EMISSION = 0x1600;
    public final static int GL_POSITION = 0x1203;
    public final static int GL_SPOT_DIRECTION = 0x1204;
    public final static int GL_AMBIENT_AND_DIFFUSE = 0x1602;
    public final static int GL_COLOR_INDEXES = 0x1603;
    public final static int GL_LIGHT_MODEL_TWO_SIDE = 0x0B52;
    public final static int GL_LIGHT_MODEL_LOCAL_VIEWER = 0x0B51;
    public final static int GL_LIGHT_MODEL_AMBIENT = 0x0B53;
    public final static int GL_FRONT_AND_BACK = 0x0408;
    public final static int GL_SHADE_MODEL = 0x0B54;
    public final static int GL_FLAT = 0x1D00;
    public final static int GL_SMOOTH = 0x1D01;
    public final static int GL_COLOR_MATERIAL = 0x0B57;
    public final static int GL_COLOR_MATERIAL_FACE = 0x0B55;
    public final static int GL_COLOR_MATERIAL_PARAMETER = 0x0B56;
    public final static int GL_NORMALIZE = 0x0BA1;
    public final static int GL_CLIP_PLANE0 = 0x3000;
    public final static int GL_CLIP_PLANE1 = 0x3001;
    public final static int GL_CLIP_PLANE2 = 0x3002;
    public final static int GL_CLIP_PLANE3 = 0x3003;
    public final static int GL_CLIP_PLANE4 = 0x3004;
    public final static int GL_CLIP_PLANE5 = 0x3005;
    public final static int GL_ACCUM_RED_BITS = 0x0D58;
    public final static int GL_ACCUM_GREEN_BITS = 0x0D59;
    public final static int GL_ACCUM_BLUE_BITS = 0x0D5A;
    public final static int GL_ACCUM_ALPHA_BITS = 0x0D5B;
    public final static int GL_ACCUM_CLEAR_VALUE = 0x0B80;
    public final static int GL_ACCUM = 0x0100;
    public final static int GL_ADD = 0x0104;
    public final static int GL_LOAD = 0x0101;
    public final static int GL_MULT = 0x0103;
    public final static int GL_RETURN = 0x0102;
    public final static int GL_ALPHA_TEST = 0x0BC0;
    public final static int GL_ALPHA_TEST_REF = 0x0BC2;
    public final static int GL_ALPHA_TEST_FUNC = 0x0BC1;
    public final static int GL_BLEND = 0x0BE2;
    public final static int GL_BLEND_SRC = 0x0BE1;
    public final static int GL_BLEND_DST = 0x0BE0;
    public final static int GL_ZERO = 0x0;
    public final static int GL_ONE = 0x1;
    public final static int GL_SRC_COLOR = 0x0300;
    public final static int GL_ONE_MINUS_SRC_COLOR = 0x0301;
    public final static int GL_SRC_ALPHA = 0x0302;
    public final static int GL_ONE_MINUS_SRC_ALPHA = 0x0303;
    public final static int GL_DST_ALPHA = 0x0304;
    public final static int GL_ONE_MINUS_DST_ALPHA = 0x0305;
    public final static int GL_DST_COLOR = 0x0306;
    public final static int GL_ONE_MINUS_DST_COLOR = 0x0307;
    public final static int GL_SRC_ALPHA_SATURATE = 0x0308;
    public final static int GL_CONSTANT_COLOR = 0x8001;
    public final static int GL_ONE_MINUS_CONSTANT_COLOR = 0x8002;
    public final static int GL_CONSTANT_ALPHA = 0x8003;
    public final static int GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004;
    public final static int GL_FEEDBACK = 0x1C01;
    public final static int GL_RENDER = 0x1C00;
    public final static int GL_SELECT = 0x1C02;
    public final static int GL_2D = 0x0600;
    public final static int GL_3D = 0x0601;
    public final static int GL_3D_COLOR = 0x0602;
    public final static int GL_3D_COLOR_TEXTURE = 0x0603;
    public final static int GL_4D_COLOR_TEXTURE = 0x0604;
    public final static int GL_POINT_TOKEN = 0x0701;
    public final static int GL_LINE_TOKEN = 0x0702;
    public final static int GL_LINE_RESET_TOKEN = 0x0707;
    public final static int GL_POLYGON_TOKEN = 0x0703;
    public final static int GL_BITMAP_TOKEN = 0x0704;
    public final static int GL_DRAW_PIXEL_TOKEN = 0x0705;
    public final static int GL_COPY_PIXEL_TOKEN = 0x0706;
    public final static int GL_PASS_THROUGH_TOKEN = 0x0700;
    public final static int GL_FEEDBACK_BUFFER_POINTER = 0x0DF0;
    public final static int GL_FEEDBACK_BUFFER_SIZE = 0x0DF1;
    public final static int GL_FEEDBACK_BUFFER_TYPE = 0x0DF2;
    public final static int GL_SELECTION_BUFFER_POINTER = 0x0DF3;
    public final static int GL_SELECTION_BUFFER_SIZE = 0x0DF4;
    public final static int GL_FOG = 0x0B60;
    public final static int GL_FOG_MODE = 0x0B65;
    public final static int GL_FOG_DENSITY = 0x0B62;
    public final static int GL_FOG_COLOR = 0x0B66;
    public final static int GL_FOG_INDEX = 0x0B61;
    public final static int GL_FOG_START = 0x0B63;
    public final static int GL_FOG_END = 0x0B64;
    public final static int GL_LINEAR = 0x2601;
    public final static int GL_EXP = 0x0800;
    public final static int GL_EXP2 = 0x0801;
    public final static int GL_LOGIC_OP = 0x0BF1;
    public final static int GL_INDEX_LOGIC_OP = 0x0BF1;
    public final static int GL_COLOR_LOGIC_OP = 0x0BF2;
    public final static int GL_LOGIC_OP_MODE = 0x0BF0;
    public final static int GL_CLEAR = 0x1500;
    public final static int GL_SET = 0x150F;
    public final static int GL_COPY = 0x1503;
    public final static int GL_COPY_INVERTED = 0x150C;
    public final static int GL_NOOP = 0x1505;
    public final static int GL_INVERT = 0x150A;
    public final static int GL_AND = 0x1501;
    public final static int GL_NAND = 0x150E;
    public final static int GL_OR = 0x1507;
    public final static int GL_NOR = 0x1508;
    public final static int GL_XOR = 0x1506;
    public final static int GL_EQUIV = 0x1509;
    public final static int GL_AND_REVERSE = 0x1502;
    public final static int GL_AND_INVERTED = 0x1504;
    public final static int GL_OR_REVERSE = 0x150B;
    public final static int GL_OR_INVERTED = 0x150D;
    public final static int GL_STENCIL_TEST = 0x0B90;
    public final static int GL_STENCIL_WRITEMASK = 0x0B98;
    public final static int GL_STENCIL_BITS = 0x0D57;
    public final static int GL_STENCIL_FUNC = 0x0B92;
    public final static int GL_STENCIL_VALUE_MASK = 0x0B93;
    public final static int GL_STENCIL_REF = 0x0B97;
    public final static int GL_STENCIL_FAIL = 0x0B94;
    public final static int GL_STENCIL_PASS_DEPTH_PASS = 0x0B96;
    public final static int GL_STENCIL_PASS_DEPTH_FAIL = 0x0B95;
    public final static int GL_STENCIL_CLEAR_VALUE = 0x0B91;
    public final static int GL_STENCIL_INDEX = 0x1901;
    public final static int GL_KEEP = 0x1E00;
    public final static int GL_REPLACE = 0x1E01;
    public final static int GL_INCR = 0x1E02;
    public final static int GL_DECR = 0x1E03;
    public final static int GL_NONE = 0x0;
    public final static int GL_LEFT = 0x0406;
    public final static int GL_RIGHT = 0x0407;
    public final static int GL_FRONT_LEFT = 0x0400;
    public final static int GL_FRONT_RIGHT = 0x0401;
    public final static int GL_BACK_LEFT = 0x0402;
    public final static int GL_BACK_RIGHT = 0x0403;
    public final static int GL_AUX0 = 0x0409;
    public final static int GL_AUX1 = 0x040A;
    public final static int GL_AUX2 = 0x040B;
    public final static int GL_AUX3 = 0x040C;
    public final static int GL_COLOR_INDEX = 0x1900;
    public final static int GL_RED = 0x1903;
    public final static int GL_GREEN = 0x1904;
    public final static int GL_BLUE = 0x1905;
    public final static int GL_ALPHA = 0x1906;
    public final static int GL_LUMINANCE = 0x1909;
    public final static int GL_LUMINANCE_ALPHA = 0x190A;
    public final static int GL_ALPHA_BITS = 0x0D55;
    public final static int GL_RED_BITS = 0x0D52;
    public final static int GL_GREEN_BITS = 0x0D53;
    public final static int GL_BLUE_BITS = 0x0D54;
    public final static int GL_INDEX_BITS = 0x0D51;
    public final static int GL_SUBPIXEL_BITS = 0x0D50;
    public final static int GL_AUX_BUFFERS = 0x0C00;
    public final static int GL_READ_BUFFER = 0x0C02;
    public final static int GL_DRAW_BUFFER = 0x0C01;
    public final static int GL_DOUBLEBUFFER = 0x0C32;
    public final static int GL_STEREO = 0x0C33;
    public final static int GL_BITMAP = 0x1A00;
    public final static int GL_COLOR = 0x1800;
    public final static int GL_DEPTH = 0x1801;
    public final static int GL_STENCIL = 0x1802;
    public final static int GL_DITHER = 0x0BD0;
    public final static int GL_RGB = 0x1907;
    public final static int GL_RGBA = 0x1908;
    public final static int GL_MAX_LIST_NESTING = 0x0B31;
    public final static int GL_MAX_ATTRIB_STACK_DEPTH = 0x0D35;
    public final static int GL_MAX_MODELVIEW_STACK_DEPTH = 0x0D36;
    public final static int GL_MAX_NAME_STACK_DEPTH = 0x0D37;
    public final static int GL_MAX_PROJECTION_STACK_DEPTH = 0x0D38;
    public final static int GL_MAX_TEXTURE_STACK_DEPTH = 0x0D39;
    public final static int GL_MAX_EVAL_ORDER = 0x0D30;
    public final static int GL_MAX_LIGHTS = 0x0D31;
    public final static int GL_MAX_CLIP_PLANES = 0x0D32;
    public final static int GL_MAX_TEXTURE_SIZE = 0x0D33;
    public final static int GL_MAX_PIXEL_MAP_TABLE = 0x0D34;
    public final static int GL_MAX_VIEWPORT_DIMS = 0x0D3A;
    public final static int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 0x0D3B;
    public final static int GL_ATTRIB_STACK_DEPTH = 0x0BB0;
    public final static int GL_CLIENT_ATTRIB_STACK_DEPTH = 0x0BB1;
    public final static int GL_COLOR_CLEAR_VALUE = 0x0C22;
    public final static int GL_COLOR_WRITEMASK = 0x0C23;
    public final static int GL_CURRENT_INDEX = 0x0B01;
    public final static int GL_CURRENT_COLOR = 0x0B00;
    public final static int GL_CURRENT_NORMAL = 0x0B02;
    public final static int GL_CURRENT_RASTER_COLOR = 0x0B04;
    public final static int GL_CURRENT_RASTER_DISTANCE = 0x0B09;
    public final static int GL_CURRENT_RASTER_INDEX = 0x0B05;
    public final static int GL_CURRENT_RASTER_POSITION = 0x0B07;
    public final static int GL_CURRENT_RASTER_TEXTURE_COORDS = 0x0B06;
    public final static int GL_CURRENT_RASTER_POSITION_VALID = 0x0B08;
    public final static int GL_CURRENT_TEXTURE_COORDS = 0x0B03;
    public final static int GL_INDEX_CLEAR_VALUE = 0x0C20;
    public final static int GL_INDEX_MODE = 0x0C30;
    public final static int GL_INDEX_WRITEMASK = 0x0C21;
    public final static int GL_MODELVIEW_MATRIX = 0x0BA6;
    public final static int GL_MODELVIEW_STACK_DEPTH = 0x0BA3;
    public final static int GL_NAME_STACK_DEPTH = 0x0D70;
    public final static int GL_PROJECTION_MATRIX = 0x0BA7;
    public final static int GL_PROJECTION_STACK_DEPTH = 0x0BA4;
    public final static int GL_RENDER_MODE = 0x0C40;
    public final static int GL_RGBA_MODE = 0x0C31;
    public final static int GL_TEXTURE_MATRIX = 0x0BA8;
    public final static int GL_TEXTURE_STACK_DEPTH = 0x0BA5;
    public final static int GL_VIEWPORT = 0x0BA2;
    public final static int GL_AUTO_NORMAL = 0x0D80;
    public final static int GL_MAP1_COLOR_4 = 0x0D90;
    public final static int GL_MAP1_GRID_DOMAIN = 0x0DD0;
    public final static int GL_MAP1_GRID_SEGMENTS = 0x0DD1;
    public final static int GL_MAP1_INDEX = 0x0D91;
    public final static int GL_MAP1_NORMAL = 0x0D92;
    public final static int GL_MAP1_TEXTURE_COORD_1 = 0x0D93;
    public final static int GL_MAP1_TEXTURE_COORD_2 = 0x0D94;
    public final static int GL_MAP1_TEXTURE_COORD_3 = 0x0D95;
    public final static int GL_MAP1_TEXTURE_COORD_4 = 0x0D96;
    public final static int GL_MAP1_VERTEX_3 = 0x0D97;
    public final static int GL_MAP1_VERTEX_4 = 0x0D98;
    public final static int GL_MAP2_COLOR_4 = 0x0DB0;
    public final static int GL_MAP2_GRID_DOMAIN = 0x0DD2;
    public final static int GL_MAP2_GRID_SEGMENTS = 0x0DD3;
    public final static int GL_MAP2_INDEX = 0x0DB1;
    public final static int GL_MAP2_NORMAL = 0x0DB2;
    public final static int GL_MAP2_TEXTURE_COORD_1 = 0x0DB3;
    public final static int GL_MAP2_TEXTURE_COORD_2 = 0x0DB4;
    public final static int GL_MAP2_TEXTURE_COORD_3 = 0x0DB5;
    public final static int GL_MAP2_TEXTURE_COORD_4 = 0x0DB6;
    public final static int GL_MAP2_VERTEX_3 = 0x0DB7;
    public final static int GL_MAP2_VERTEX_4 = 0x0DB8;
    public final static int GL_COEFF = 0x0A00;
    public final static int GL_DOMAIN = 0x0A02;
    public final static int GL_ORDER = 0x0A01;
    public final static int GL_FOG_HINT = 0x0C54;
    public final static int GL_LINE_SMOOTH_HINT = 0x0C52;
    public final static int GL_PERSPECTIVE_CORRECTION_HINT = 0x0C50;
    public final static int GL_POINT_SMOOTH_HINT = 0x0C51;
    public final static int GL_POLYGON_SMOOTH_HINT = 0x0C53;
    public final static int GL_DONT_CARE = 0x1100;
    public final static int GL_FASTEST = 0x1101;
    public final static int GL_NICEST = 0x1102;
    public final static int GL_SCISSOR_TEST = 0x0C11;
    public final static int GL_SCISSOR_BOX = 0x0C10;
    public final static int GL_MAP_COLOR = 0x0D10;
    public final static int GL_MAP_STENCIL = 0x0D11;
    public final static int GL_INDEX_SHIFT = 0x0D12;
    public final static int GL_INDEX_OFFSET = 0x0D13;
    public final static int GL_RED_SCALE = 0x0D14;
    public final static int GL_RED_BIAS = 0x0D15;
    public final static int GL_GREEN_SCALE = 0x0D18;
    public final static int GL_GREEN_BIAS = 0x0D19;
    public final static int GL_BLUE_SCALE = 0x0D1A;
    public final static int GL_BLUE_BIAS = 0x0D1B;
    public final static int GL_ALPHA_SCALE = 0x0D1C;
    public final static int GL_ALPHA_BIAS = 0x0D1D;
    public final static int GL_DEPTH_SCALE = 0x0D1E;
    public final static int GL_DEPTH_BIAS = 0x0D1F;
    public final static int GL_PIXEL_MAP_S_TO_S_SIZE = 0x0CB1;
    public final static int GL_PIXEL_MAP_I_TO_I_SIZE = 0x0CB0;
    public final static int GL_PIXEL_MAP_I_TO_R_SIZE = 0x0CB2;
    public final static int GL_PIXEL_MAP_I_TO_G_SIZE = 0x0CB3;
    public final static int GL_PIXEL_MAP_I_TO_B_SIZE = 0x0CB4;
    public final static int GL_PIXEL_MAP_I_TO_A_SIZE = 0x0CB5;
    public final static int GL_PIXEL_MAP_R_TO_R_SIZE = 0x0CB6;
    public final static int GL_PIXEL_MAP_G_TO_G_SIZE = 0x0CB7;
    public final static int GL_PIXEL_MAP_B_TO_B_SIZE = 0x0CB8;
    public final static int GL_PIXEL_MAP_A_TO_A_SIZE = 0x0CB9;
    public final static int GL_PIXEL_MAP_S_TO_S = 0x0C71;
    public final static int GL_PIXEL_MAP_I_TO_I = 0x0C70;
    public final static int GL_PIXEL_MAP_I_TO_R = 0x0C72;
    public final static int GL_PIXEL_MAP_I_TO_G = 0x0C73;
    public final static int GL_PIXEL_MAP_I_TO_B = 0x0C74;
    public final static int GL_PIXEL_MAP_I_TO_A = 0x0C75;
    public final static int GL_PIXEL_MAP_R_TO_R = 0x0C76;
    public final static int GL_PIXEL_MAP_G_TO_G = 0x0C77;
    public final static int GL_PIXEL_MAP_B_TO_B = 0x0C78;
    public final static int GL_PIXEL_MAP_A_TO_A = 0x0C79;
    public final static int GL_PACK_ALIGNMENT = 0x0D05;
    public final static int GL_PACK_LSB_FIRST = 0x0D01;
    public final static int GL_PACK_ROW_LENGTH = 0x0D02;
    public final static int GL_PACK_SKIP_PIXELS = 0x0D04;
    public final static int GL_PACK_SKIP_ROWS = 0x0D03;
    public final static int GL_PACK_SWAP_BYTES = 0x0D00;
    public final static int GL_UNPACK_ALIGNMENT = 0x0CF5;
    public final static int GL_UNPACK_LSB_FIRST = 0x0CF1;
    public final static int GL_UNPACK_ROW_LENGTH = 0x0CF2;
    public final static int GL_UNPACK_SKIP_PIXELS = 0x0CF4;
    public final static int GL_UNPACK_SKIP_ROWS = 0x0CF3;
    public final static int GL_UNPACK_SWAP_BYTES = 0x0CF0;
    public final static int GL_ZOOM_X = 0x0D16;
    public final static int GL_ZOOM_Y = 0x0D17;
    public final static int GL_TEXTURE_ENV = 0x2300;
    public final static int GL_TEXTURE_ENV_MODE = 0x2200;
    public final static int GL_TEXTURE_1D = 0x0DE0;
    public final static int GL_TEXTURE_2D = 0x0DE1;
    public final static int GL_TEXTURE_WRAP_S = 0x2802;
    public final static int GL_TEXTURE_WRAP_T = 0x2803;
    public final static int GL_TEXTURE_MAG_FILTER = 0x2800;
    public final static int GL_TEXTURE_MIN_FILTER = 0x2801;
    public final static int GL_TEXTURE_ENV_COLOR = 0x2201;
    public final static int GL_TEXTURE_GEN_S = 0x0C60;
    public final static int GL_TEXTURE_GEN_T = 0x0C61;
    public final static int GL_TEXTURE_GEN_MODE = 0x2500;
    public final static int GL_TEXTURE_BORDER_COLOR = 0x1004;
    public final static int GL_TEXTURE_WIDTH = 0x1000;
    public final static int GL_TEXTURE_HEIGHT = 0x1001;
    public final static int GL_TEXTURE_BORDER = 0x1005;
    public final static int GL_TEXTURE_COMPONENTS = 0x1003;
    public final static int GL_TEXTURE_RED_SIZE = 0x805C;
    public final static int GL_TEXTURE_GREEN_SIZE = 0x805D;
    public final static int GL_TEXTURE_BLUE_SIZE = 0x805E;
    public final static int GL_TEXTURE_ALPHA_SIZE = 0x805F;
    public final static int GL_TEXTURE_LUMINANCE_SIZE = 0x8060;
    public final static int GL_TEXTURE_INTENSITY_SIZE = 0x8061;
    public final static int GL_NEAREST_MIPMAP_NEAREST = 0x2700;
    public final static int GL_NEAREST_MIPMAP_LINEAR = 0x2702;
    public final static int GL_LINEAR_MIPMAP_NEAREST = 0x2701;
    public final static int GL_LINEAR_MIPMAP_LINEAR = 0x2703;
    public final static int GL_OBJECT_LINEAR = 0x2401;
    public final static int GL_OBJECT_PLANE = 0x2501;
    public final static int GL_EYE_LINEAR = 0x2400;
    public final static int GL_EYE_PLANE = 0x2502;
    public final static int GL_SPHERE_MAP = 0x2402;
    public final static int GL_DECAL = 0x2101;
    public final static int GL_MODULATE = 0x2100;
    public final static int GL_NEAREST = 0x2600;
    public final static int GL_REPEAT = 0x2901;
    public final static int GL_CLAMP = 0x2900;
    public final static int GL_S = 0x2000;
    public final static int GL_T = 0x2001;
    public final static int GL_R = 0x2002;
    public final static int GL_Q = 0x2003;
    public final static int GL_TEXTURE_GEN_R = 0x0C62;
    public final static int GL_TEXTURE_GEN_Q = 0x0C63;
    public final static int GL_VENDOR = 0x1F00;
    public final static int GL_RENDERER = 0x1F01;
    public final static int GL_VERSION = 0x1F02;
    public final static int GL_EXTENSIONS = 0x1F03;
    public final static int GL_NO_ERROR = 0x0;
    public final static int GL_INVALID_VALUE = 0x0501;
    public final static int GL_INVALID_ENUM = 0x0500;
    public final static int GL_INVALID_OPERATION = 0x0502;
    public final static int GL_STACK_OVERFLOW = 0x0503;
    public final static int GL_STACK_UNDERFLOW = 0x0504;
    public final static int GL_OUT_OF_MEMORY = 0x0505;
    public final static int GL_CURRENT_BIT = 0x00000001;
    public final static int GL_POINT_BIT = 0x00000002;
    public final static int GL_LINE_BIT = 0x00000004;
    public final static int GL_POLYGON_BIT = 0x00000008;
    public final static int GL_POLYGON_STIPPLE_BIT = 0x00000010;
    public final static int GL_PIXEL_MODE_BIT = 0x00000020;
    public final static int GL_LIGHTING_BIT = 0x00000040;
    public final static int GL_FOG_BIT = 0x00000080;
    public final static int GL_DEPTH_BUFFER_BIT = 0x00000100;
    public final static int GL_ACCUM_BUFFER_BIT = 0x00000200;
    public final static int GL_STENCIL_BUFFER_BIT = 0x00000400;
    public final static int GL_VIEWPORT_BIT = 0x00000800;
    public final static int GL_TRANSFORM_BIT = 0x00001000;
    public final static int GL_ENABLE_BIT = 0x00002000;
    public final static int GL_COLOR_BUFFER_BIT = 0x00004000;
    public final static int GL_HINT_BIT = 0x00008000;
    public final static int GL_EVAL_BIT = 0x00010000;
    public final static int GL_LIST_BIT = 0x00020000;
    public final static int GL_TEXTURE_BIT = 0x00040000;
    public final static int GL_SCISSOR_BIT = 0x00080000;
    public final static int GL_ALL_ATTRIB_BITS = 0x000FFFFF;
    public final static int GL_PROXY_TEXTURE_1D = 0x8063;
    public final static int GL_PROXY_TEXTURE_2D = 0x8064;
    public final static int GL_TEXTURE_PRIORITY = 0x8066;
    public final static int GL_TEXTURE_RESIDENT = 0x8067;
    public final static int GL_TEXTURE_BINDING_1D = 0x8068;
    public final static int GL_TEXTURE_BINDING_2D = 0x8069;
    public final static int GL_TEXTURE_INTERNAL_FORMAT = 0x1003;
    public final static int GL_ALPHA4 = 0x803B;
    public final static int GL_ALPHA8 = 0x803C;
    public final static int GL_ALPHA12 = 0x803D;
    public final static int GL_ALPHA16 = 0x803E;
    public final static int GL_LUMINANCE4 = 0x803F;
    public final static int GL_LUMINANCE8 = 0x8040;
    public final static int GL_LUMINANCE12 = 0x8041;
    public final static int GL_LUMINANCE16 = 0x8042;
    public final static int GL_LUMINANCE4_ALPHA4 = 0x8043;
    public final static int GL_LUMINANCE6_ALPHA2 = 0x8044;
    public final static int GL_LUMINANCE8_ALPHA8 = 0x8045;
    public final static int GL_LUMINANCE12_ALPHA4 = 0x8046;
    public final static int GL_LUMINANCE12_ALPHA12 = 0x8047;
    public final static int GL_LUMINANCE16_ALPHA16 = 0x8048;
    public final static int GL_INTENSITY = 0x8049;
    public final static int GL_INTENSITY4 = 0x804A;
    public final static int GL_INTENSITY8 = 0x804B;
    public final static int GL_INTENSITY12 = 0x804C;
    public final static int GL_INTENSITY16 = 0x804D;
    public final static int GL_R3_G3_B2 = 0x2A10;
    public final static int GL_RGB4 = 0x804F;
    public final static int GL_RGB5 = 0x8050;
    public final static int GL_RGB8 = 0x8051;
    public final static int GL_RGB10 = 0x8052;
    public final static int GL_RGB12 = 0x8053;
    public final static int GL_RGB16 = 0x8054;
    public final static int GL_RGBA2 = 0x8055;
    public final static int GL_RGBA4 = 0x8056;
    public final static int GL_RGB5_A1 = 0x8057;
    public final static int GL_RGBA8 = 0x8058;
    public final static int GL_RGB10_A2 = 0x8059;
    public final static int GL_RGBA12 = 0x805A;
    public final static int GL_RGBA16 = 0x805B;
    public final static int GL_CLIENT_PIXEL_STORE_BIT = 0x00000001;
    public final static int GL_CLIENT_VERTEX_ARRAY_BIT = 0x00000002;
    public final static int GL_ALL_CLIENT_ATTRIB_BITS = 0xFFFFFFFF;
    public final static int GL_CLIENT_ALL_ATTRIB_BITS = 0xFFFFFFFF;


    ////////////////////////////////////////////////////////////////////////////////
    // OPENGL 1.0 / 1.1 Functions

    void glClearIndex(float c);

    void glClearColor(float red, float green, float blue, float alpha);

    void glClear(long mask);

    void glIndexMask(long mask);

    void glColorMask(short red, short green, short blue, short alpha);

    void glAlphaFunc(long func, float ref);

    void glBlendFunc(long sfactor, long dfactor);

    void glLogicOp(long opcode);

    void glCullFace(long mode);

    void glFrontFace(long mode);

    void glPointSize(float size);

    void glLineWidth(float width);

    void glLineStipple(int factor, int pattern);

    void glPolygonMode(long face, long mode);

    void glPolygonOffset(float factor, float units);

    void glPolygonStipple(ShortBuffer mask);

    void glGetPolygonStipple(ShortBuffer mask);

    void glEdgeFlag(short flag);

    void glEdgeFlagv(ShortBuffer flag);

    void glScissor(int x, int y, int width, int height);

    void glClipPlane(long plane, DoubleBuffer equation);

    void glGetClipPlane(long plane, DoubleBuffer equation);

    void glDrawBuffer(long mode);

    void glReadBuffer(long mode);

    void glEnable(long cap);

    void glDisable(long cap);

    short glIsEnabled(long cap);

    void glEnableClientState(long cap);

    void glDisableClientState(long cap);

    void glGetBooleanv(long pname, ShortBuffer params);

    void glGetDoublev(long pname, DoubleBuffer params);

    void glGetFloatv(long pname, FloatBuffer params);

    void glGetIntegerv(long pname, IntBuffer params);

    void glPushAttrib(long mask);

    void glPopAttrib();

    void glPushClientAttrib(long mask);

    void glPopClientAttrib();

    int glRenderMode(long mode);

    long glGetError();

    ShortBuffer glGetString(long name);

    void glFinish();

    void glFlush();

    void glHint(long target, long mode);

    void glClearDepth(double depth);

    void glDepthFunc(long func);

    void glDepthMask(short flag);

    void glDepthRange(double near_val, double far_val);

    void glClearAccum(float red, float green, float blue, float alpha);

    void glAccum(long op, float value);

    void glMatrixMode(long mode);

    void glOrtho(double left, double right, double bottom, double top, double near_val, double far_val);

    void glFrustum(double left, double right, double bottom, double top, double near_val, double far_val);

    void glViewport(int x, int y, int width, int height);

    void glPushMatrix();

    void glPopMatrix();

    void glLoadIdentity();

    void glLoadMatrixd(DoubleBuffer m);

    void glLoadMatrixf(FloatBuffer m);

    void glMultMatrixd(DoubleBuffer m);

    void glMultMatrixf(FloatBuffer m);

    void glRotated(double angle, double x, double y, double z);

    void glRotatef(float angle, float x, float y, float z);

    void glScaled(double x, double y, double z);

    void glScalef(float x, float y, float z);

    void glTranslated(double x, double y, double z);

    void glTranslatef(float x, float y, float z);

    short glIsList(long list);

    void glDeleteLists(long list, int range);

    long glGenLists(int range);

    void glNewList(long list, long mode);

    void glEndList();

    void glCallList(long list);

    void glCallLists(int n, long type, Buffer lists);

    void glListBase(long base);

    void glBegin(long mode);

    void glEnd();

    void glVertex2d(double x, double y);

    void glVertex2f(float x, float y);

    void glVertex2i(int x, int y);

    void glVertex2s(short x, short y);

    void glVertex3d(double x, double y, double z);

    void glVertex3f(float x, float y, float z);

    void glVertex3i(int x, int y, int z);

    void glVertex3s(short x, short y, short z);

    void glVertex4d(double x, double y, double z, double w);

    void glVertex4f(float x, float y, float z, float w);

    void glVertex4i(int x, int y, int z, int w);

    void glVertex4s(short x, short y, short z, short w);

    void glVertex2dv(DoubleBuffer v);

    void glVertex2fv(FloatBuffer v);

    void glVertex2iv(IntBuffer v);

    void glVertex2sv(ShortBuffer v);

    void glVertex3dv(DoubleBuffer v);

    void glVertex3fv(FloatBuffer v);

    void glVertex3iv(IntBuffer v);

    void glVertex3sv(ShortBuffer v);

    void glVertex4dv(DoubleBuffer v);

    void glVertex4fv(FloatBuffer v);

    void glVertex4iv(IntBuffer v);

    void glVertex4sv(ShortBuffer v);

    void glNormal3b(byte nx, byte ny, byte nz);

    void glNormal3d(double nx, double ny, double nz);

    void glNormal3f(float nx, float ny, float nz);

    void glNormal3i(int nx, int ny, int nz);

    void glNormal3s(short nx, short ny, short nz);

    void glNormal3bv(ByteBuffer v);

    void glNormal3dv(DoubleBuffer v);

    void glNormal3fv(FloatBuffer v);

    void glNormal3iv(IntBuffer v);

    void glNormal3sv(ShortBuffer v);

    void glIndexd(double c);

    void glIndexf(float c);

    void glIndexi(int c);

    void glIndexs(short c);

    void glIndexub(short c);

    void glIndexdv(DoubleBuffer c);

    void glIndexfv(FloatBuffer c);

    void glIndexiv(IntBuffer c);

    void glIndexsv(ShortBuffer c);

    void glIndexubv(ShortBuffer c);

    void glColor3b(byte red, byte green, byte blue);

    void glColor3d(double red, double green, double blue);

    void glColor3f(float red, float green, float blue);

    void glColor3i(int red, int green, int blue);

    void glColor3s(short red, short green, short blue);

    void glColor3ub(short red, short green, short blue);

    void glColor3ui(long red, long green, long blue);

    void glColor3us(int red, int green, int blue);

    void glColor4b(byte red, byte green, byte blue, byte alpha);

    void glColor4d(double red, double green, double blue, double alpha);

    void glColor4f(float red, float green, float blue, float alpha);

    void glColor4i(int red, int green, int blue, int alpha);

    void glColor4s(short red, short green, short blue, short alpha);

    void glColor4ub(short red, short green, short blue, short alpha);

    void glColor4ui(long red, long green, long blue, long alpha);

    void glColor4us(int red, int green, int blue, int alpha);

    void glColor3bv(ByteBuffer v);

    void glColor3dv(DoubleBuffer v);

    void glColor3fv(FloatBuffer v);

    void glColor3iv(IntBuffer v);

    void glColor3sv(ShortBuffer v);

    void glColor3ubv(ShortBuffer v);

    void glColor3uiv(IntBuffer v);

    void glColor3usv(IntBuffer v);

    void glColor4bv(ByteBuffer v);

    void glColor4dv(DoubleBuffer v);

    void glColor4fv(FloatBuffer v);

    void glColor4iv(IntBuffer v);

    void glColor4sv(ShortBuffer v);

    void glColor4ubv(ShortBuffer v);

    void glColor4uiv(IntBuffer v);

    void glColor4usv(IntBuffer v);

    void glTexCoord1d(double s);

    void glTexCoord1f(float s);

    void glTexCoord1i(int s);

    void glTexCoord1s(short s);

    void glTexCoord2d(double s, double t);

    void glTexCoord2f(float s, float t);

    void glTexCoord2i(int s, int t);

    void glTexCoord2s(short s, short t);

    void glTexCoord3d(double s, double t, double r);

    void glTexCoord3f(float s, float t, float r);

    void glTexCoord3i(int s, int t, int r);

    void glTexCoord3s(short s, short t, short r);

    void glTexCoord4d(double s, double t, double r, double q);

    void glTexCoord4f(float s, float t, float r, float q);

    void glTexCoord4i(int s, int t, int r, int q);

    void glTexCoord4s(short s, short t, short r, short q);

    void glTexCoord1dv(DoubleBuffer v);

    void glTexCoord1fv(FloatBuffer v);

    void glTexCoord1iv(IntBuffer v);

    void glTexCoord1sv(ShortBuffer v);

    void glTexCoord2dv(DoubleBuffer v);

    void glTexCoord2fv(FloatBuffer v);

    void glTexCoord2iv(IntBuffer v);

    void glTexCoord2sv(ShortBuffer v);

    void glTexCoord3dv(DoubleBuffer v);

    void glTexCoord3fv(FloatBuffer v);

    void glTexCoord3iv(IntBuffer v);

    void glTexCoord3sv(ShortBuffer v);

    void glTexCoord4dv(DoubleBuffer v);

    void glTexCoord4fv(FloatBuffer v);

    void glTexCoord4iv(IntBuffer v);

    void glTexCoord4sv(ShortBuffer v);

    void glRasterPos2d(double x, double y);

    void glRasterPos2f(float x, float y);

    void glRasterPos2i(int x, int y);

    void glRasterPos2s(short x, short y);

    void glRasterPos3d(double x, double y, double z);

    void glRasterPos3f(float x, float y, float z);

    void glRasterPos3i(int x, int y, int z);

    void glRasterPos3s(short x, short y, short z);

    void glRasterPos4d(double x, double y, double z, double w);

    void glRasterPos4f(float x, float y, float z, float w);

    void glRasterPos4i(int x, int y, int z, int w);

    void glRasterPos4s(short x, short y, short z, short w);

    void glRasterPos2dv(DoubleBuffer v);

    void glRasterPos2fv(FloatBuffer v);

    void glRasterPos2iv(IntBuffer v);

    void glRasterPos2sv(ShortBuffer v);

    void glRasterPos3dv(DoubleBuffer v);

    void glRasterPos3fv(FloatBuffer v);

    void glRasterPos3iv(IntBuffer v);

    void glRasterPos3sv(ShortBuffer v);

    void glRasterPos4dv(DoubleBuffer v);

    void glRasterPos4fv(FloatBuffer v);

    void glRasterPos4iv(IntBuffer v);

    void glRasterPos4sv(ShortBuffer v);

    void glRectd(double x1, double y1, double x2, double y2);

    void glRectf(float x1, float y1, float x2, float y2);

    void glRecti(int x1, int y1, int x2, int y2);

    void glRects(short x1, short y1, short x2, short y2);

    void glRectdv(DoubleBuffer v1, DoubleBuffer v2);

    void glRectfv(FloatBuffer v1, FloatBuffer v2);

    void glRectiv(IntBuffer v1, IntBuffer v2);

    void glRectsv(ShortBuffer v1, ShortBuffer v2);

    void glVertexPointer(int size, long type, int stride, Buffer ptr);

    void glNormalPointer(long type, int stride, Buffer ptr);

    void glColorPointer(int size, long type, int stride, Buffer ptr);

    void glIndexPointer(long type, int stride, Buffer ptr);

    void glTexCoordPointer(int size, long type, int stride, Buffer ptr);

    void glEdgeFlagPointer(int stride, Buffer ptr);

    //void glGetPointerv(long pname, SWIGTYPE_p_p_void params);

    void glArrayElement(int i);

    void glDrawArrays(long mode, int first, int count);

    void glDrawElements(long mode, int count, long type, Buffer indices);

    void glInterleavedArrays(long format, int stride, Buffer pointer);

    void glShadeModel(long mode);

    void glLightf(long light, long pname, float param);

    void glLighti(long light, long pname, int param);

    void glLightfv(long light, long pname, FloatBuffer params);

    void glLightiv(long light, long pname, IntBuffer params);

    void glGetLightfv(long light, long pname, FloatBuffer params);

    void glGetLightiv(long light, long pname, IntBuffer params);

    void glLightModelf(long pname, float param);

    void glLightModeli(long pname, int param);

    void glLightModelfv(long pname, FloatBuffer params);

    void glLightModeliv(long pname, IntBuffer params);

    void glMaterialf(long face, long pname, float param);

    void glMateriali(long face, long pname, int param);

    void glMaterialfv(long face, long pname, FloatBuffer params);

    void glMaterialiv(long face, long pname, IntBuffer params);

    void glGetMaterialfv(long face, long pname, FloatBuffer params);

    void glGetMaterialiv(long face, long pname, IntBuffer params);

    void glColorMaterial(long face, long mode);

    void glPixelZoom(float xfactor, float yfactor);

    void glPixelStoref(long pname, float param);

    void glPixelStorei(long pname, int param);

    void glPixelTransferf(long pname, float param);

    void glPixelTransferi(long pname, int param);

    void glPixelMapfv(long map, int mapsize, FloatBuffer values);

    void glPixelMapuiv(long map, int mapsize, IntBuffer values);

    void glPixelMapusv(long map, int mapsize, IntBuffer values);

    void glGetPixelMapfv(long map, FloatBuffer values);

    void glGetPixelMapuiv(long map, IntBuffer values);

    void glGetPixelMapusv(long map, IntBuffer values);

    void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ShortBuffer bitmap);

    void glReadPixels(int x, int y, int width, int height, long format, long type, Buffer pixels);

    void glDrawPixels(int width, int height, long format, long type, Buffer pixels);

    void glCopyPixels(int x, int y, int width, int height, long type);

    void glStencilFunc(long func, int ref, long mask);

    void glStencilMask(long mask);

    void glStencilOp(long fail, long zfail, long zpass);

    void glClearStencil(int s);

    void glTexGend(long coord, long pname, double param);

    void glTexGenf(long coord, long pname, float param);

    void glTexGeni(long coord, long pname, int param);

    void glTexGendv(long coord, long pname, DoubleBuffer params);

    void glTexGenfv(long coord, long pname, FloatBuffer params);

    void glTexGeniv(long coord, long pname, IntBuffer params);

    void glGetTexGendv(long coord, long pname, DoubleBuffer params);

    void glGetTexGenfv(long coord, long pname, FloatBuffer params);

    void glGetTexGeniv(long coord, long pname, IntBuffer params);

    void glTexEnvf(long target, long pname, float param);

    void glTexEnvi(long target, long pname, int param);

    void glTexEnvfv(long target, long pname, FloatBuffer params);

    void glTexEnviv(long target, long pname, IntBuffer params);

    void glGetTexEnvfv(long target, long pname, FloatBuffer params);

    void glGetTexEnviv(long target, long pname, IntBuffer params);

    void glTexParameterf(long target, long pname, float param);

    void glTexParameteri(long target, long pname, int param);

    void glTexParameterfv(long target, long pname, FloatBuffer params);

    void glTexParameteriv(long target, long pname, IntBuffer params);

    void glGetTexParameterfv(long target, long pname, FloatBuffer params);

    void glGetTexParameteriv(long target, long pname, IntBuffer params);

    void glGetTexLevelParameterfv(long target, int level, long pname, FloatBuffer params);

    void glGetTexLevelParameteriv(long target, int level, long pname, IntBuffer params);

    void glTexImage1D(long target, int level, int internalFormat, int width, int border, long format, long type, Buffer pixels);

    void glTexImage2D(long target, int level, int internalFormat, int width, int height, int border, long format, long type, Buffer pixels);

    void glGetTexImage(long target, int level, long format, long type, Buffer pixels);

    void glGenTextures(int n, IntBuffer textures);

    void glDeleteTextures(int n, IntBuffer textures);

    void glBindTexture(long target, long texture);

    void glPrioritizeTextures(int n, IntBuffer textures, FloatBuffer priorities);

    short glAreTexturesResident(int n, IntBuffer textures, ShortBuffer residences);

    short glIsTexture(long texture);

    void glTexSubImage1D(long target, int level, int xoffset, int width, long format, long type, Buffer pixels);

    void glTexSubImage2D(long target, int level, int xoffset, int yoffset, int width, int height, long format, long type, Buffer pixels);

    void glCopyTexImage1D(long target, int level, long internalformat, int x, int y, int width, int border);

    void glCopyTexImage2D(long target, int level, long internalformat, int x, int y, int width, int height, int border);

    void glCopyTexSubImage1D(long target, int level, int xoffset, int x, int y, int width);

    void glCopyTexSubImage2D(long target, int level, int xoffset, int yoffset, int x, int y, int width, int height);

    void glMap1d(long target, double u1, double u2, int stride, int order, DoubleBuffer points);

    void glMap1f(long target, float u1, float u2, int stride, int order, FloatBuffer points);

    void glMap2d(long target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points);

    void glMap2f(long target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points);

    void glGetMapdv(long target, long query, DoubleBuffer v);

    void glGetMapfv(long target, long query, FloatBuffer v);

    void glGetMapiv(long target, long query, IntBuffer v);

    void glEvalCoord1d(double u);

    void glEvalCoord1f(float u);

    void glEvalCoord1dv(DoubleBuffer u);

    void glEvalCoord1fv(FloatBuffer u);

    void glEvalCoord2d(double u, double v);

    void glEvalCoord2f(float u, float v);

    void glEvalCoord2dv(DoubleBuffer u);

    void glEvalCoord2fv(FloatBuffer u);

    void glMapGrid1d(int un, double u1, double u2);

    void glMapGrid1f(int un, float u1, float u2);

    void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2);

    void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);

    void glEvalPoint1(int i);

    void glEvalPoint2(int i, int j);

    void glEvalMesh1(long mode, int i1, int i2);

    void glEvalMesh2(long mode, int i1, int i2, int j1, int j2);

    void glFogf(long pname, float param);

    void glFogi(long pname, int param);

    void glFogfv(long pname, FloatBuffer params);

    void glFogiv(long pname, IntBuffer params);

    void glFeedbackBuffer(int size, long type, FloatBuffer buffer);

    void glPassThrough(float token);

    void glSelectBuffer(int size, IntBuffer buffer);

    void glInitNames();

    void glLoadName(long name);

    void glPushName(long name);

    void glPopName();

    //
    // NATIVE ARRAYS
     void glPolygonStipple(short[] jarg1);
     void glGetPolygonStipple(short[] jarg1);
     void glEdgeFlagv(short[] jarg1);
     void glClipPlane(long jarg1, double[] jarg2);
     void glGetClipPlane(long jarg1, double[] jarg2);
     void glGetBooleanv(long jarg1, short[] jarg2);
     void glGetDoublev(long jarg1, double[] jarg2);
     void glGetFloatv(long jarg1, float[] jarg2);
     void glGetIntegerv(long jarg1, int[] jarg2);
     void glLoadMatrixd(double[] jarg1);
     void glLoadMatrixf(float[] jarg1);
     void glMultMatrixd(double[] jarg1);
     void glMultMatrixf(float[] jarg1);
     void glVertex2dv(double[] jarg1);
     void glVertex2fv(float[] jarg1);
     void glVertex2iv(int[] jarg1);
     void glVertex2sv(short[] jarg1);
     void glVertex3dv(double[] jarg1);
     void glVertex3fv(float[] jarg1);
     void glVertex3iv(int[] jarg1);
     void glVertex3sv(short[] jarg1);
     void glVertex4dv(double[] jarg1);
     void glVertex4fv(float[] jarg1);
     void glVertex4iv(int[] jarg1);
     void glVertex4sv(short[] jarg1);
     void glNormal3bv(byte[] jarg1);
     void glNormal3dv(double[] jarg1);
     void glNormal3fv(float[] jarg1);
     void glNormal3iv(int[] jarg1);
     void glNormal3sv(short[] jarg1);
     void glIndexdv(double[] jarg1);
     void glIndexfv(float[] jarg1);
     void glIndexiv(int[] jarg1);
     void glIndexsv(short[] jarg1);
     void glIndexubv(short[] jarg1);
     void glColor3bv(byte[] jarg1);
     void glColor3dv(double[] jarg1);
     void glColor3fv(float[] jarg1);
     void glColor3iv(int[] jarg1);
     void glColor3sv(short[] jarg1);
     void glColor3ubv(short[] jarg1);
     void glColor3uiv(long[] jarg1);
     void glColor3usv(int[] jarg1);
     void glColor4bv(byte[] jarg1);
     void glColor4dv(double[] jarg1);
     void glColor4fv(float[] jarg1);
     void glColor4iv(int[] jarg1);
     void glColor4sv(short[] jarg1);
     void glColor4ubv(short[] jarg1);
     void glColor4uiv(long[] jarg1);
     void glColor4usv(int[] jarg1);
     void glTexCoord1dv(double[] jarg1);
     void glTexCoord1fv(float[] jarg1);
     void glTexCoord1iv(int[] jarg1);
     void glTexCoord1sv(short[] jarg1);
     void glTexCoord2dv(double[] jarg1);
     void glTexCoord2fv(float[] jarg1);
     void glTexCoord2iv(int[] jarg1);
     void glTexCoord2sv(short[] jarg1);
     void glTexCoord3dv(double[] jarg1);
     void glTexCoord3fv(float[] jarg1);
     void glTexCoord3iv(int[] jarg1);
     void glTexCoord3sv(short[] jarg1);
     void glTexCoord4dv(double[] jarg1);
     void glTexCoord4fv(float[] jarg1);
     void glTexCoord4iv(int[] jarg1);
     void glTexCoord4sv(short[] jarg1);
     void glRasterPos2dv(double[] jarg1);
     void glRasterPos2fv(float[] jarg1);
     void glRasterPos2iv(int[] jarg1);
     void glRasterPos2sv(short[] jarg1);
     void glRasterPos3dv(double[] jarg1);
     void glRasterPos3fv(float[] jarg1);
     void glRasterPos3iv(int[] jarg1);
     void glRasterPos3sv(short[] jarg1);
     void glRasterPos4dv(double[] jarg1);
     void glRasterPos4fv(float[] jarg1);
     void glRasterPos4iv(int[] jarg1);
     void glRasterPos4sv(short[] jarg1);
     void glRectdv(double[] jarg1, double[] jarg2);
     void glRectfv(float[] jarg1, float[] jarg2);
     void glRectiv(int[] jarg1, int[] jarg2);
     void glRectsv(short[] jarg1, short[] jarg2);
     void glLightfv(long jarg1, long jarg2, float[] jarg3);
     void glLightiv(long jarg1, long jarg2, int[] jarg3);
     void glGetLightfv(long jarg1, long jarg2, float[] jarg3);
     void glGetLightiv(long jarg1, long jarg2, int[] jarg3);
     void glLightModelfv(long jarg1, float[] jarg2);
     void glLightModeliv(long jarg1, int[] jarg2);
     void glMaterialfv(long jarg1, long jarg2, float[] jarg3);
     void glMaterialiv(long jarg1, long jarg2, int[] jarg3);
     void glGetMaterialfv(long jarg1, long jarg2, float[] jarg3);
     void glGetMaterialiv(long jarg1, long jarg2, int[] jarg3);
     void glPixelMapfv(long jarg1, int jarg2, float[] jarg3);
     void glPixelMapuiv(long jarg1, int jarg2, long[] jarg3);
     void glPixelMapusv(long jarg1, int jarg2, int[] jarg3);
     void glGetPixelMapfv(long jarg1, float[] jarg2);
     void glGetPixelMapuiv(long jarg1, long[] jarg2);
     void glGetPixelMapusv(long jarg1, int[] jarg2);
     void glBitmap(int jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6, short[] jarg7);
     void glTexGendv(long jarg1, long jarg2, double[] jarg3);
     void glTexGenfv(long jarg1, long jarg2, float[] jarg3);
     void glTexGeniv(long jarg1, long jarg2, int[] jarg3);
     void glGetTexGendv(long jarg1, long jarg2, double[] jarg3);
     void glGetTexGenfv(long jarg1, long jarg2, float[] jarg3);
     void glGetTexGeniv(long jarg1, long jarg2, int[] jarg3);
     void glTexEnvfv(long jarg1, long jarg2, float[] jarg3);
     void glTexEnviv(long jarg1, long jarg2, int[] jarg3);
     void glGetTexEnvfv(long jarg1, long jarg2, float[] jarg3);
     void glGetTexEnviv(long jarg1, long jarg2, int[] jarg3);
     void glTexParameterfv(long jarg1, long jarg2, float[] jarg3);
     void glTexParameteriv(long jarg1, long jarg2, int[] jarg3);
     void glGetTexParameterfv(long jarg1, long jarg2, float[] jarg3);
     void glGetTexParameteriv(long jarg1, long jarg2, int[] jarg3);
     void glGetTexLevelParameterfv(long jarg1, int jarg2, long jarg3, float[] jarg4);
     void glGetTexLevelParameteriv(long jarg1, int jarg2, long jarg3, int[] jarg4);
     void glGenTextures(int jarg1, long[] jarg2);
     void glDeleteTextures(int jarg1, long[] jarg2);
     void glPrioritizeTextures(int jarg1, long[] jarg2, float[] jarg3);
     short glAreTexturesResident(int jarg1, long[] jarg2, short[] jarg3);
     void glMap1d(long jarg1, double jarg2, double jarg3, int jarg4, int jarg5, double[] jarg6);
     void glMap1f(long jarg1, float jarg2, float jarg3, int jarg4, int jarg5, float[] jarg6);
     void glMap2d(long jarg1, double jarg2, double jarg3, int jarg4, int jarg5, double jarg6, double jarg7, int jarg8, int jarg9, double[] jarg10);
     void glMap2f(long jarg1, float jarg2, float jarg3, int jarg4, int jarg5, float jarg6, float jarg7, int jarg8, int jarg9, float[] jarg10);
     void glGetMapdv(long jarg1, long jarg2, double[] jarg3);
     void glGetMapfv(long jarg1, long jarg2, float[] jarg3);
     void glGetMapiv(long jarg1, long jarg2, int[] jarg3);
     void glEvalCoord1dv(double[] jarg1);
     void glEvalCoord1fv(float[] jarg1);
     void glEvalCoord2dv(double[] jarg1);
     void glEvalCoord2fv(float[] jarg1);
     void glFogfv(long jarg1, float[] jarg2);
     void glFogiv(long jarg1, int[] jarg2);
     void glFeedbackBuffer(int jarg1, long jarg2, float[] jarg3);
     void glSelectBuffer(int jarg1, long[] jarg2);

    ////////////////////////////////////////////////////////////////////////////////
    // GLU Constants
    public final static int GLU_EXT_object_space_tess = 1;
    public final static int GLU_EXT_nurbs_tessellator = 1;
    public final static int GLU_FALSE = 0;
    public final static int GLU_TRUE = 1;
    public final static int GLU_VERSION_1_1 = 1;
    public final static int GLU_VERSION_1_2 = 1;
    public final static int GLU_VERSION_1_3 = 1;
    public final static int GLU_VERSION = 100800;
    public final static int GLU_EXTENSIONS = 100801;
    public final static int GLU_INVALID_ENUM = 100900;
    public final static int GLU_INVALID_VALUE = 100901;
    public final static int GLU_OUT_OF_MEMORY = 100902;
    public final static int GLU_INVALID_OPERATION = 100904;
    public final static int GLU_OUTLINE_POLYGON = 100240;
    public final static int GLU_OUTLINE_PATCH = 100241;
    public final static int GLU_NURBS_ERROR = 100103;
    public final static int GLU_ERROR = 100103;
    public final static int GLU_NURBS_BEGIN = 100164;
    public final static int GLU_NURBS_BEGIN_EXT = 100164;
    public final static int GLU_NURBS_VERTEX = 100165;
    public final static int GLU_NURBS_VERTEX_EXT = 100165;
    public final static int GLU_NURBS_NORMAL = 100166;
    public final static int GLU_NURBS_NORMAL_EXT = 100166;
    public final static int GLU_NURBS_COLOR = 100167;
    public final static int GLU_NURBS_COLOR_EXT = 100167;
    public final static int GLU_NURBS_TEXTURE_COORD = 100168;
    public final static int GLU_NURBS_TEX_COORD_EXT = 100168;
    public final static int GLU_NURBS_END = 100169;
    public final static int GLU_NURBS_END_EXT = 100169;
    public final static int GLU_NURBS_BEGIN_DATA = 100170;
    public final static int GLU_NURBS_BEGIN_DATA_EXT = 100170;
    public final static int GLU_NURBS_VERTEX_DATA = 100171;
    public final static int GLU_NURBS_VERTEX_DATA_EXT = 100171;
    public final static int GLU_NURBS_NORMAL_DATA = 100172;
    public final static int GLU_NURBS_NORMAL_DATA_EXT = 100172;
    public final static int GLU_NURBS_COLOR_DATA = 100173;
    public final static int GLU_NURBS_COLOR_DATA_EXT = 100173;
    public final static int GLU_NURBS_TEXTURE_COORD_DATA = 100174;
    public final static int GLU_NURBS_TEX_COORD_DATA_EXT = 100174;
    public final static int GLU_NURBS_END_DATA = 100175;
    public final static int GLU_NURBS_END_DATA_EXT = 100175;
    public final static int GLU_NURBS_ERROR1 = 100251;
    public final static int GLU_NURBS_ERROR2 = 100252;
    public final static int GLU_NURBS_ERROR3 = 100253;
    public final static int GLU_NURBS_ERROR4 = 100254;
    public final static int GLU_NURBS_ERROR5 = 100255;
    public final static int GLU_NURBS_ERROR6 = 100256;
    public final static int GLU_NURBS_ERROR7 = 100257;
    public final static int GLU_NURBS_ERROR8 = 100258;
    public final static int GLU_NURBS_ERROR9 = 100259;
    public final static int GLU_NURBS_ERROR10 = 100260;
    public final static int GLU_NURBS_ERROR11 = 100261;
    public final static int GLU_NURBS_ERROR12 = 100262;
    public final static int GLU_NURBS_ERROR13 = 100263;
    public final static int GLU_NURBS_ERROR14 = 100264;
    public final static int GLU_NURBS_ERROR15 = 100265;
    public final static int GLU_NURBS_ERROR16 = 100266;
    public final static int GLU_NURBS_ERROR17 = 100267;
    public final static int GLU_NURBS_ERROR18 = 100268;
    public final static int GLU_NURBS_ERROR19 = 100269;
    public final static int GLU_NURBS_ERROR20 = 100270;
    public final static int GLU_NURBS_ERROR21 = 100271;
    public final static int GLU_NURBS_ERROR22 = 100272;
    public final static int GLU_NURBS_ERROR23 = 100273;
    public final static int GLU_NURBS_ERROR24 = 100274;
    public final static int GLU_NURBS_ERROR25 = 100275;
    public final static int GLU_NURBS_ERROR26 = 100276;
    public final static int GLU_NURBS_ERROR27 = 100277;
    public final static int GLU_NURBS_ERROR28 = 100278;
    public final static int GLU_NURBS_ERROR29 = 100279;
    public final static int GLU_NURBS_ERROR30 = 100280;
    public final static int GLU_NURBS_ERROR31 = 100281;
    public final static int GLU_NURBS_ERROR32 = 100282;
    public final static int GLU_NURBS_ERROR33 = 100283;
    public final static int GLU_NURBS_ERROR34 = 100284;
    public final static int GLU_NURBS_ERROR35 = 100285;
    public final static int GLU_NURBS_ERROR36 = 100286;
    public final static int GLU_NURBS_ERROR37 = 100287;
    public final static int GLU_AUTO_LOAD_MATRIX = 100200;
    public final static int GLU_CULLING = 100201;
    public final static int GLU_SAMPLING_TOLERANCE = 100203;
    public final static int GLU_DISPLAY_MODE = 100204;
    public final static int GLU_PARAMETRIC_TOLERANCE = 100202;
    public final static int GLU_SAMPLING_METHOD = 100205;
    public final static int GLU_U_STEP = 100206;
    public final static int GLU_V_STEP = 100207;
    public final static int GLU_NURBS_MODE = 100160;
    public final static int GLU_NURBS_MODE_EXT = 100160;
    public final static int GLU_NURBS_TESSELLATOR = 100161;
    public final static int GLU_NURBS_TESSELLATOR_EXT = 100161;
    public final static int GLU_NURBS_RENDERER = 100162;
    public final static int GLU_NURBS_RENDERER_EXT = 100162;
    public final static int GLU_OBJECT_PARAMETRIC_ERROR = 100208;
    public final static int GLU_OBJECT_PARAMETRIC_ERROR_EXT = 100208;
    public final static int GLU_OBJECT_PATH_LENGTH = 100209;
    public final static int GLU_OBJECT_PATH_LENGTH_EXT = 100209;
    public final static int GLU_PATH_LENGTH = 100215;
    public final static int GLU_PARAMETRIC_ERROR = 100216;
    public final static int GLU_DOMAIN_DISTANCE = 100217;
    public final static int GLU_MAP1_TRIM_2 = 100210;
    public final static int GLU_MAP1_TRIM_3 = 100211;
    public final static int GLU_POINT = 100010;
    public final static int GLU_LINE = 100011;
    public final static int GLU_FILL = 100012;
    public final static int GLU_SILHOUETTE = 100013;
    public final static int GLU_SMOOTH = 100000;
    public final static int GLU_FLAT = 100001;
    public final static int GLU_NONE = 100002;
    public final static int GLU_OUTSIDE = 100020;
    public final static int GLU_INSIDE = 100021;
    public final static int GLU_TESS_BEGIN = 100100;
    public final static int GLU_BEGIN = 100100;
    public final static int GLU_TESS_VERTEX = 100101;
    public final static int GLU_VERTEX = 100101;
    public final static int GLU_TESS_END = 100102;
    public final static int GLU_END = 100102;
    public final static int GLU_TESS_ERROR = 100103;
    public final static int GLU_TESS_EDGE_FLAG = 100104;
    public final static int GLU_EDGE_FLAG = 100104;
    public final static int GLU_TESS_COMBINE = 100105;
    public final static int GLU_TESS_BEGIN_DATA = 100106;
    public final static int GLU_TESS_VERTEX_DATA = 100107;
    public final static int GLU_TESS_END_DATA = 100108;
    public final static int GLU_TESS_ERROR_DATA = 100109;
    public final static int GLU_TESS_EDGE_FLAG_DATA = 100110;
    public final static int GLU_TESS_COMBINE_DATA = 100111;
    public final static int GLU_CW = 100120;
    public final static int GLU_CCW = 100121;
    public final static int GLU_INTERIOR = 100122;
    public final static int GLU_EXTERIOR = 100123;
    public final static int GLU_UNKNOWN = 100124;
    public final static int GLU_TESS_WINDING_RULE = 100140;
    public final static int GLU_TESS_BOUNDARY_ONLY = 100141;
    public final static int GLU_TESS_TOLERANCE = 100142;
    public final static int GLU_TESS_ERROR1 = 100151;
    public final static int GLU_TESS_ERROR2 = 100152;
    public final static int GLU_TESS_ERROR3 = 100153;
    public final static int GLU_TESS_ERROR4 = 100154;
    public final static int GLU_TESS_ERROR5 = 100155;
    public final static int GLU_TESS_ERROR6 = 100156;
    public final static int GLU_TESS_ERROR7 = 100157;
    public final static int GLU_TESS_ERROR8 = 100158;
    public final static int GLU_TESS_MISSING_BEGIN_POLYGON = 100151;
    public final static int GLU_TESS_MISSING_BEGIN_CONTOUR = 100152;
    public final static int GLU_TESS_MISSING_END_POLYGON = 100153;
    public final static int GLU_TESS_MISSING_END_CONTOUR = 100154;
    public final static int GLU_TESS_COORD_TOO_LARGE = 100155;
    public final static int GLU_TESS_NEED_COMBINE_CALLBACK = 100156;
    public final static int GLU_TESS_WINDING_ODD = 100130;
    public final static int GLU_TESS_WINDING_NONZERO = 100131;
    public final static int GLU_TESS_WINDING_POSITIVE = 100132;
    public final static int GLU_TESS_WINDING_NEGATIVE = 100133;
    public final static int GLU_TESS_WINDING_ABS_GEQ_TWO = 100134;
    public final static double GLU_TESS_MAX_COORD = 1.0e150;

    //////////////////////////////////////////////////////////////////////
    // GLU Functions
    public  void gluBeginCurve(GLUnurbs nurb);
    public  void gluBeginPolygon(GLUtesselator tess);
    public  void gluBeginSurface(GLUnurbs nurb);
    public  void gluBeginTrim(GLUnurbs nurb);
    //    public  int gluBuild1DMipmapLevels(long target, int internalFormat, int width, long format, long type, int level, int base, int max, Buffer data);// not avail on windows
    public  int gluBuild1DMipmaps(long target, int internalFormat, int width, long format, long type, Buffer data);
    //    public  int gluBuild2DMipmapLevels(long target, int internalFormat, int width, int height, long format, long type, int level, int base, int max, Buffer data);// not avail on windows
    public  int gluBuild2DMipmaps(long target, int internalFormat, int width, int height, long format, long type, Buffer data);
    //    public  int gluBuild3DMipmapLevels(long target, int internalFormat, int width, int height, int depth, long format, long type, int level, int base, int max, Buffer data);// not avail on windows
    //    public  int gluBuild3DMipmaps(long target, int internalFormat, int width, int height, int depth, long format, long type, Buffer data);
    //    public  short gluCheckExtension(ShortBuffer extName, ShortBuffer extString);// not avail on windows
    public  void gluCylinder(GLUquadric quad, double base, double top, double height, int slices, int stacks);
    public  void gluDeleteNurbsRenderer(GLUnurbs nurb);
    public  void gluDeleteQuadric(GLUquadric quad);
    public  void gluDeleteTess(GLUtesselator tess);
    public  void gluDisk(GLUquadric quad, double inner, double outer, int slices, int loops);
    public  void gluEndCurve(GLUnurbs nurb);
    public  void gluEndPolygon(GLUtesselator tess);
    public  void gluEndSurface(GLUnurbs nurb);
    public  void gluEndTrim(GLUnurbs nurb);
    public  ShortBuffer gluErrorString(long error);
    public  void gluGetNurbsProperty(GLUnurbs nurb, long property, FloatBuffer data);
    public  ShortBuffer gluGetString(long name);
    public  void gluGetTessProperty(GLUtesselator tess, long which, DoubleBuffer data);
    public  void gluLoadSamplingMatrices(GLUnurbs nurb, FloatBuffer model, FloatBuffer perspective, IntBuffer view);
    public  void gluLookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ);
    public  GLUnurbs gluNewNurbsRenderer();
    public  GLUquadric gluNewQuadric();
    public  GLUtesselator gluNewTess();
    public  void gluNextContour(GLUtesselator tess, long type);
    //public  void gluNurbsCallback(GLUnurbs nurb, long which, SWIGTYPE_p__GLUfuncptr CallBackFunc);
    //public  void gluNurbsCallbackData(GLUnurbs nurb, Buffer userData);
    //public  void gluNurbsCallbackDataEXT(GLUnurbs nurb, Buffer userData);
    public  void gluNurbsCurve(GLUnurbs nurb, int knotCount, FloatBuffer knots, int stride, FloatBuffer control, int order, long type);
    public  void gluNurbsProperty(GLUnurbs nurb, long property, float value);
    public  void gluNurbsSurface(GLUnurbs nurb, int sKnotCount, FloatBuffer sKnots, int tKnotCount, FloatBuffer tKnots, int sStride, int tStride, FloatBuffer control, int sOrder, int tOrder, long type);
    public  void gluOrtho2D(double left, double right, double bottom, double top);
    public  void gluPartialDisk(GLUquadric quad, double inner, double outer, int slices, int loops, double start, double sweep);
    public  void gluPerspective(double fovy, double aspect, double zNear, double zFar);
    public  void gluPickMatrix(double x, double y, double delX, double delY, IntBuffer viewport);
    public  int gluProject(double objX, double objY, double objZ, DoubleBuffer model, DoubleBuffer proj, IntBuffer view, DoubleBuffer winX, DoubleBuffer winY, DoubleBuffer winZ);
    public  void gluPwlCurve(GLUnurbs nurb, int count, FloatBuffer data, int stride, long type);
    //public  void gluQuadricCallback(GLUquadric quad, long which, SWIGTYPE_p__GLUfuncptr CallBackFunc);
    public  void gluQuadricDrawStyle(GLUquadric quad, long draw);
    public  void gluQuadricNormals(GLUquadric quad, long normal);
    public  void gluQuadricOrientation(GLUquadric quad, long orientation);
    public  void gluQuadricTexture(GLUquadric quad, short texture);
    public  int gluScaleImage(long format, int wIn, int hIn, long typeIn, Buffer dataIn, int wOut, int hOut, long typeOut, Buffer dataOut);
    public  void gluSphere(GLUquadric quad, double radius, int slices, int stacks);
    public  void gluTessBeginContour(GLUtesselator tess);
    public  void gluTessBeginPolygon(GLUtesselator tess, Buffer data);
    //public  void gluTessCallback(GLUtesselator tess, long which, SWIGTYPE_p__GLUfuncptr CallBackFunc);
    public  void gluTessEndContour(GLUtesselator tess);
    public  void gluTessEndPolygon(GLUtesselator tess);
    public  void gluTessNormal(GLUtesselator tess, double valueX, double valueY, double valueZ);
    public  void gluTessProperty(GLUtesselator tess, long which, double data);
    public  void gluTessVertex(GLUtesselator tess, DoubleBuffer location, Buffer data);
    public  int gluUnProject(double winX, double winY, double winZ, DoubleBuffer model, DoubleBuffer proj, IntBuffer view, DoubleBuffer objX, DoubleBuffer objY, DoubleBuffer objZ);
    //    public  int gluUnProject4(double winX, double winY, double winZ, double clipW, DoubleBuffer model, DoubleBuffer proj, IntBuffer view, double near, double far, DoubleBuffer objX, DoubleBuffer objY, DoubleBuffer objZ, DoubleBuffer objW);// not avail on windows
}