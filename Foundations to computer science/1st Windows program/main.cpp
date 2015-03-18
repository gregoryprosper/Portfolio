#include <windows.h>

const wchar_t g_szClassName[] = L"myWindowClass";

// Step 4: the Window Procedure
LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
	HDC hDC;			// handle to the device context (permission to draw)
	PAINTSTRUCT Ps;		// a variable that you need
	HPEN hBlackPen;		// the handle to the red pen
	HBRUSH hBrush;

	static int penSize = 5;
	static int sx=0;
	static int sy=0;

	
	static int x1=0;
	static int y1=0;
	static int x2=0;
	static int y2=0;
	static int y3=0;
	POINT points[3] = {350 + x1,150 + y1,375,106.7 + y3,400 + x2,150 + y2};
	
	static int red1=255;
	static int red2=0;
	static int red3=0;
	static int blue1=0;
	static int blue2=255;
	static int blue3=0;
	static int green1=0;
	static int green2=0;
	static int green3=255;

    switch(msg)
    {
		case WM_PAINT:
			hDC = BeginPaint(hwnd,&Ps);
			hBlackPen = CreatePen(PS_SOLID,penSize,RGB(0,0,0));
			SelectObject(hDC,hBlackPen);

			hBrush = CreateSolidBrush(RGB(red1,blue1,green1));
			SelectObject(hDC,hBrush);
			
			Ellipse(hDC,200,100,250,150);

			hBrush = CreateSolidBrush(RGB(red2,blue2,green2));
			SelectObject(hDC,hBrush);

			Rectangle(hDC,50 + sx,100 + sy,100 + sx,150 + sy);

			hBrush = CreateSolidBrush(RGB(red3,blue3,green3));
			SelectObject(hDC,hBrush);

			Polygon(hDC,points,3);

			DeleteObject(hBlackPen);
			DeleteObject(hBrush);
			EndPaint(hwnd,&Ps);
		break;
		case WM_KEYDOWN:
			if(wParam==VK_RIGHT)
			{
				sx += 3;
			}
			else if (wParam==VK_LEFT)
			{
				sx -= 3;
			}
			else if (wParam==VK_UP)
			{
				sy -= 3;
			}
			else if (wParam==VK_DOWN)
			{
				sy += 3;
			}
			else if (wParam==VK_PRIOR)
			{
				x1 -= 1;
				x2 += 1;
				y1 += 1;
				y2 += 1;
				y3 -= 1;

			}
			else if (wParam==VK_NEXT)
			{
				x1 += 2;
				x2 -= 2;
				y1 -= 2;
				y2 -= 2;
				y3 += 2;

			}
			else if(wParam==VK_SPACE)
			{
				red1=rand()%256;
				green1=rand()%256;
				blue1=rand()%256;
				red2=rand()%256;
				green2=rand()%256;
				blue2=rand()%256;
				red3=rand()%256;
				green3=rand()%256;
				blue3=rand()%256;
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
        L"Gregory Prosper",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 600, 400,
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