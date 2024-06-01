# SDL笔记

## 层次结构

![image-20240511101636826](./assets/image-20240511101636826.png)



## 流程

![](./assets/image-20240511103013919.png)





## SDL常用函数

![image-20240511104755586](./assets/image-20240511104755586.png)



## SDL数据结构

![image-20240511104954488](./assets/image-20240511104954488.png)



```c++
#include <SDL.h>
#include <iostream>
 
//用户自定义事件
#define SDL_USER_DEF_REFRESH_EVENT	(SDL_USEREVENT + 1)   //请求画面刷新事件
#define SDL_USER_DEF_QUIT_EVENT		(SDL_USEREVENT + 2)   //推出
 
//定义分辨率
//YUV像素分辨率
#define		YUV_WIDTH		320
#define		YUV_HEIGHT		240
//定义YUV格式
#define		YUV_FORMAT		SDL_PIXELFORMAT_IYUV
 
int   s_thread_exit = 0;	//
 
int RefreshVideoThread(void* pData)
{
	while (!s_thread_exit)
	{
		SDL_Event  event;
		event.type = SDL_USER_DEF_REFRESH_EVENT;
		SDL_PushEvent(&event);
		SDL_Delay(40);
	}
 
	s_thread_exit = 0;
 
	//push quit event
	SDL_Event  event;
	event.type = SDL_USER_DEF_QUIT_EVENT;
	SDL_PushEvent(&event);
	return 0;
}
 
int main(int argc, char* argv[])
{
	if (SDL_Init(SDL_INIT_VIDEO))
	{
		fprintf(stderr, "Failed to initialize SDL - %s\n", SDL_GetError());
		return -1;
	}
 
	//SDL
	SDL_Event	event;
	SDL_Rect	rect;
	SDL_Window* window = nullptr;
	SDL_Renderer *pRenderer = nullptr;		//渲染
	SDL_Texture  *pTexture = nullptr;		//纹理
	SDL_Thread	*pThread = nullptr;			//线程
	uint32_t	pixFormat = YUV_FORMAT;		//YUV420, 即 SDL_PIXELFORMAT_IYUV
 
	//1. yuv的分辨率
	int nVideoWidth = YUV_WIDTH;
	int nVideoHeight = YUV_HEIGHT;
	//2. 显示窗口的分辨率、
	int nWinWidth = YUV_WIDTH;
	int nWinHeight = YUV_HEIGHT;
	 //yuv句柄
	FILE* pVideoFd = nullptr;
	const char *szYuvPath = "test.yuv";
 
	size_t nVideoBuffLen = 0;
	uint8_t  *pVideoBuf = nullptr;
 
	//测试文件时YUV420P格式
	uint32_t  y_frame_len = nVideoWidth * nVideoHeight;
	uint32_t  u_frame_len = nVideoWidth * nVideoHeight / 4;
	uint32_t  v_frame_len = nVideoWidth * nVideoHeight / 4;
	uint32_t  yuv_frame_len = y_frame_len + u_frame_len + v_frame_len;
 
	//创建窗口
	window = SDL_CreateWindow(("YUV Basic Window"),
		SDL_WINDOWPOS_UNDEFINED,
		SDL_WINDOWPOS_UNDEFINED,
		nVideoWidth,
		nVideoHeight,
		SDL_WINDOW_OPENGL | SDL_WINDOW_RESIZABLE);
 
	if (!window)
	{
		std::cout << "Can't Create window, err:" << SDL_GetError() << std::endl;
		goto _FAILED_;
	}
 
	//基于窗口创建 渲染器
	pRenderer = SDL_CreateRenderer(window, -1, 0);
 
	//基于渲染器创建纹理
	pTexture = SDL_CreateTexture(pRenderer, pixFormat, SDL_TEXTUREACCESS_STREAMING,
		nVideoWidth, nVideoHeight);
 
	//分配空间
	pVideoBuf = (uint8_t*)malloc(yuv_frame_len);
	if (!pVideoBuf)
	{
		fprintf(stderr, "Failed to alloc yuv frame \n");
		goto _FAILED_;
	}
 
	//打开YUV文件
	pVideoFd = fopen(szYuvPath, "rb");
	if (!pVideoFd)
	{
		fprintf(stderr, "Failed to open yuv frame \n");
		goto _FAILED_;
	}
 
	//请求刷新线程
	pThread = SDL_CreateThread(RefreshVideoThread, nullptr, nullptr);
 
 
	while (true)
	{
		//收取SDL分配的事件
		SDL_WaitEvent(&event);
 
		switch (event.type)
		{
		case SDL_USER_DEF_REFRESH_EVENT:
		{
			//读取视频
			nVideoBuffLen = fread(pVideoBuf, 1, yuv_frame_len, pVideoFd);
			if (nVideoBuffLen < 0)
			{
				fprintf(stderr, "failed to Read data from yuv file\n");
				goto _FAILED_;
			}
			//设置纹理的数据
			SDL_UpdateTexture(pTexture, nullptr, pVideoBuf, nWinWidth);
 
			//显示区域,通过修改 w和h进行缩放
			rect.x = 0;
			rect.y = 0;
			float w_ratio = nWinWidth * 1.0 / nVideoWidth;
			float h_ratio = nWinHeight * 1.0 / nVideoHeight;
			rect.w = nVideoWidth * w_ratio;
			rect.h = nVideoHeight * h_ratio;
 
			//清除当前显示
			SDL_RenderClear(pRenderer);
			//将纹理的数据拷贝给渲染器
			SDL_RenderCopy(pRenderer, pTexture, nullptr, &rect);
			//显示
			SDL_RenderPresent(pRenderer);
			break;
		}
		case SDL_WINDOWEVENT:
		{
			// if resize
			SDL_GetWindowSize(window, &nWinWidth, &nWinHeight);
			printf("window size: w:%d, h:%d", nWinWidth, nWinHeight);
			break;
		}
		case SDL_QUIT:		//退出事件
		{
			s_thread_exit = 1;
			break;
		}
		case SDL_USER_DEF_QUIT_EVENT:
		{
			break;
		}
		}
		
	}
 
_FAILED_:
	s_thread_exit = 1; //线程退出
	if (pThread)
		SDL_WaitThread(pThread, nullptr); //等待线程退出
	if (pVideoBuf)
		free(pVideoBuf);
	if (pVideoFd)
		fclose(pVideoFd);
	if (pTexture)
		SDL_DestroyTexture(pTexture);
	if (pRenderer)
		SDL_DestroyRenderer(pRenderer);
	if(window)
		SDL_DestroyWindow(window);
 
	SDL_Quit();
 
	return 0;
}
```

