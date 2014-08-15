%module SWIG_SDLJoystick
%{
  #include "SDL_joystick.h"
%}

%javaconst(1);
%import "SDL_types.h"
%include typemaps.i


#define SDL_HAT_CENTERED	0x00
#define SDL_HAT_UP		0x01
#define SDL_HAT_RIGHT		0x02
#define SDL_HAT_DOWN		0x04
#define SDL_HAT_LEFT		0x08
#define SDL_HAT_RIGHTUP		(SDL_HAT_RIGHT|SDL_HAT_UP)
#define SDL_HAT_RIGHTDOWN	(SDL_HAT_RIGHT|SDL_HAT_DOWN)
#define SDL_HAT_LEFTUP		(SDL_HAT_LEFT|SDL_HAT_UP)
#define SDL_HAT_LEFTDOWN	(SDL_HAT_LEFT|SDL_HAT_DOWN)

struct _SDL_Joystick;
typedef struct _SDL_Joystick SDL_Joystick;

extern int  SDL_NumJoysticks(void);
extern const char *  SDL_JoystickName(int device_index);
extern SDL_Joystick *   SDL_JoystickOpen(int device_index);
extern int  SDL_JoystickOpened(int device_index);
extern int  SDL_JoystickIndex(SDL_Joystick *joystick);
extern int  SDL_JoystickNumAxes(SDL_Joystick *joystick);
extern int  SDL_JoystickNumBalls(SDL_Joystick *joystick);
extern int  SDL_JoystickNumHats(SDL_Joystick *joystick);
extern int  SDL_JoystickNumButtons(SDL_Joystick *joystick);
extern void  SDL_JoystickUpdate(void);
extern int  SDL_JoystickEventState(int state);
extern Sint16  SDL_JoystickGetAxis(SDL_Joystick *joystick, int axis);
extern Uint8  SDL_JoystickGetHat(SDL_Joystick *joystick, int hat);
extern int  SDL_JoystickGetBall(SDL_Joystick *joystick, int ball, int *OUTPUT, int *OUTPUT);
extern Uint8  SDL_JoystickGetButton(SDL_Joystick *joystick, int button);
extern void  SDL_JoystickClose(SDL_Joystick *joystick);


