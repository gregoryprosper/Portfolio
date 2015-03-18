//http://www.winprog.org/tutorial/simple_window.html

#include <windows.h>

const wchar_t g_szClassName[] = L"myWindowClass";

// Step 4: the Window Procedure
LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
	HDC hDC;			// handle to the device context (permission to draw)
	PAINTSTRUCT Ps;		// a variable that you need
	HPEN hRedPen;		// the handle to the red pen
	HBRUSH hBrush;
	static bool isEllipse = true;

	static int x=200;
	static int y=200;
	static int red=255;
	static int blue=0;
	static int green=0;
	static int red2=0;
	static int blue2=0;
	static int green2=0;
	static int penSize = 5;
	HFONT hFont;		// the handle to the font
	RECT rect;
	int fontHeight = 100;


    switch(msg)
    {
		case WM_PAINT:
			hDC = BeginPaint(hwnd,&Ps);
			hRedPen=CreatePen(PS_SOLID,penSize,RGB(red,green,blue));
			SelectObject(hDC,hRedPen);

			hBrush=CreateSolidBrush(RGB(red2,green2,blue2));
			SelectObject(hDC,hBrush);


			//Create fonts
			fontHeight=40;
			//hFont=CreateFont(fontHeight,0,0,0,0,0,0,0,0,0,0,0,0,L"Times New Roman");
			hFont=CreateFont(fontHeight,0,0,0,0,1,0,0,0,0,0,0,0,L"Arial");
			SelectObject(hDC,hFont);


			if(isEllipse)
			{
				Ellipse(hDC,x-100,y-50,x+100,y+50);
			}
			else
			{
				Rectangle(hDC,x-100,y-50,x+100,y+50);
			}

			// Draw some text
			rect.top=350;
			rect.bottom=500;
			rect.left = 50;
			rect.right = 700;

			//DrawText(hDC,L"Press r, e, c, s or the arrow keys",-1,&rect, DT_CENTER | DT_WORDBREAK);			//Centered
			//DrawText(hDC,L"Press r, e, c, s or the arrow keys",-1,&rect, DT_CENTER );			//Centered
			DrawText(hDC,L"Press r, e, c, s or the arrow keys",-1,&rect, DT_CENTER | DT_SINGLELINE );			//Centered



			DeleteObject(hBrush);
			DeleteObject(hFont);
			DeleteObject(hRedPen);
			EndPaint(hwnd,&Ps);
			break;
		case WM_KEYDOWN:
			if(wParam==VK_RIGHT)
			{
				x+=3;
			}
			else if(wParam==VK_LEFT)
			{
				x-=3;
			}
			else if(wParam==VK_UP)
			{
				y-=3;
			}
			else if(wParam==VK_DOWN)
			{
				y+=3;
			}
			InvalidateRect(hwnd,NULL,true);
			break;
		case WM_CHAR:
			if(wParam=='e')
			{
				isEllipse=true;
			}
			else if(wParam=='r')
			{
				isEllipse=false;
			}
			else if(wParam=='c')
			{
				red=rand()%256;
				green=rand()%256;
				blue=rand()%256;
				red2=rand()%256;
				green2=rand()%256;
				blue2=rand()%256;
			}
			else if(wParam=='s')
			{
				penSize=(rand()%15)+1;
			}
			InvalidateRect(hwnd,NULL,true);
		break;
        case WM_CLOSE:
            DestroyWindow(hwnd);
        break;
        case WM_DESTROY:
            PostQuitMessage(0);
        break;
        default:
            return DefWindowProc(hwnd, msg, wParam, lParam);
    }
    return 0;
}

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
    LPSTR lpCmdLine, int nCmdShow)
{
    WNDCLASSEX wc;
    HWND hwnd;
    MSG Msg;

    //Step 1: Registering the Window Class
    wc.cbSize        = sizeof(WNDCLASSEX);
    wc.style         = 0;
    wc.lpfnWndProc   = WndProc;
    wc.cbClsExtra    = 0;
    wc.cbWndExtra    = 0;
    wc.hInstance     = hInstance;
    wc.hIcon         = LoadIcon(NULL, IDI_APPLICATION);
    wc.hCursor       = LoadCursor(NULL, IDC_ARROW);
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    wc.lpszMenuName  = NULL;
    wc.lpszClassName = g_szClassName;
    wc.hIconSm       = LoadIcon(NULL, IDI_APPLICATION);

    if(!RegisterClassEx(&wc))
    {
        MessageBox(NULL, L"Window Registration Failed!", L"Error!",
            MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    // Step 2: Creating the Window
    hwnd = CreateWindowEx(
        WS_EX_CLIENTEDGE,
        g_szClassName,
        L"Tom's Dumb Window",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 800, 600,
        NULL, NULL, hInstance, NULL);

    if(hwnd == NULL)
    {
        MessageBox(NULL, L"Window Creation Failed!", L"Error!",
            MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    ShowWindow(hwnd, nCmdShow);
    UpdateWindow(hwnd);

    // Step 3: The Message Loop
    while(GetMessage(&Msg, NULL, 0, 0) > 0)
    {
        TranslateMessage(&Msg);
        DispatchMessage(&Msg);
    }
    return Msg.wParam;
}