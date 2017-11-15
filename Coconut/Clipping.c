#include<stdio.h>
#include<graphics.h>
#include<math.h>
void clipping(float,float,float);
int j =0;
float x1[8],v1[8];
void main()
{
	int gd=DETECT,gm, i,n;
	float x[8],y[8],slope;
	printf("coordinates : ");
	for(i=0;i<4;i++)
	{
		scanf("%f%f",&x[i],&y[i]);
	}
	initgraph(&gd,&gm,"");
	cleardevice();
	outtextxy(10,10,"Before clipping");
	outtextxy(10,470,"Press any key....");
	rectangle(0,0,300,300);
	for(i=0;i<3;i++)
	{
		line(x[i],y[i],x[i+1],y[i+1]);
	}
	line(x[i],y[i],x[0],y[0]);
	getch();
	cleardevice();
	for(i=0;i<3;i++)
	{
		slope=(y[i+1]-y[i])/(x[i+1]-x[i]);
		clipping(x[i],y[i],slope);
		clipping(x[i+1],y[i+1],slope);
	}
	slope=(y[i]-y[0])/(x[i]-x[0]);
	clipping(x[i],y[i],slope);
	clipping(x[0],y[0],slope);
	outtextxy(10,10,"After clipping");
	outtextxy(10,470,"Press any key....");
	rectangle(0,0,300,300);
	for(i=0;i<j-1;i++)
		line(x1[i],v1[i],x1[i+1],v1[i+1]);
	getch();
}

void clipping(float edge,float ve,float slope)
{
	while(edge<0 || edge>300 || ve<0 || ve>300)
	{
		if(edge<0)
		{
			ve=ve+slope*(0-edge);
			edge=0;
		}
		else if(edge>300)
		{
			ve=ve+slope*(300-edge);
			edge=300;
		}
		if(ve<0)
		{
			edge=edge+(0-ve)/slope;
			ve=0;
		}
		else if(ve>300)
		{
			edge=edge+(300-ve)/slope;
			ve=300;
		}
	}
	x1[j]=edge;
	v1[j]=ve;
	j++;
}
