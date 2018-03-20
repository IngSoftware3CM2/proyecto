#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <math.h>

typedef struct file_header File_Header;
struct file_header{
	char Type[2];
	int Size;
	short Reserved1;
	short Reserved2;
	int OffBits;
};

typedef struct image_header Image_Header;
struct image_header{
	int Size;
	int Width;
	int Height;
	short Planes;
	short BitCount;
	int Compression;
	int SizeImage;
	int XPelsPerMeter;
	int YPelsPerMeter;
	int ClrUsed;
	int ClrImportant;
};
////////////////////////////////////////////////////
typedef struct colorpalete ColorPalete;
struct colorpalete
{
	char bgr;
	//int green;
	//int red;
};
///////////////////////////////////////////////////////
typedef struct bitmap Bitmap;
struct bitmap{
	File_Header fh;
	Image_Header ih; 
	ColorPalete bgr;
	unsigned char *data;
	unsigned char **img;
};

void bmp_print(Bitmap *bmp){
	printf("\nFile Header");
	printf("\nType: %c%c",bmp->fh.Type[0],bmp->fh.Type[1]);
	printf("\nSize: %d",bmp->fh.Size);
	printf("\nReserved1: %d",bmp->fh.Reserved1);
	printf("\nReserved2: %d",bmp->fh.Reserved2);
	printf("\nOffBits: %d",bmp->fh.OffBits);
	printf("\n\nFile Header");
	printf("\nSize: %d",bmp->ih.Size);
	printf("\nWidth: %d",bmp->ih.Width);
	printf("\nHeight: %d",bmp->ih.Height);
	printf("\nPlanes: %d",bmp->ih.Planes);
	printf("\nBitCount: %d",bmp->ih.BitCount);
	printf("\nCompression: %d",bmp->ih.Compression);
	printf("\nSizeImage: %d",bmp->ih.SizeImage);
	printf("\nXPelsPerMeter: %d",bmp->ih.XPelsPerMeter);
	printf("\nYPelsPerMeter: %d",bmp->ih.YPelsPerMeter);
	printf("\nClrUsed: %d",bmp->ih.ClrUsed);
	printf("\nClrImportant: %d\n",bmp->ih.ClrImportant);
}

Bitmap *bmp_open(char *file){
	FILE *f1;
	Bitmap *bmp=NULL;
	f1=fopen(file,"rb");
	if(f1==NULL){
		printf("\nNo se puede abrir el archivo: %s",file);
		getch();
		exit(-1);
	}
	bmp=(Bitmap*)malloc(sizeof(Bitmap));
	fread(bmp->fh.Type,1,2,f1);
	if(bmp->fh.Type[0]!='B' || bmp->fh.Type[1]!='M'){
		printf("\nEl archivo: ''%s'', no es bitmap\n",file);
		getch();
		exit(-1);
	}
	fread(&bmp->fh.Size,4,1,f1);
	fread(&bmp->fh.Reserved1,2,1,f1);
	fread(&bmp->fh.Reserved2,2,1,f1);
	fread(&bmp->fh.OffBits,4,1,f1);
	fread(&bmp->ih.Size,4,1,f1);
	fread(&bmp->ih.Width,4,1,f1);
	fread(&bmp->ih.Height,4,1,f1);
	fread(&bmp->ih.Planes,2,1,f1);
	fread(&bmp->ih.BitCount,2,1,f1);
	fread(&bmp->ih.Compression,4,1,f1);
	fread(&bmp->ih.SizeImage,4,1,f1);
	fread(&bmp->ih.XPelsPerMeter,4,1,f1);
	fread(&bmp->ih.YPelsPerMeter,4,1,f1);
	fread(&bmp->ih.ClrUsed,4,1,f1);
	fread(&bmp->ih.ClrImportant,4,1,f1);
	bmp->data=(unsigned char *)malloc(bmp->ih.SizeImage);
	fread(bmp->data,1,bmp->ih.SizeImage,f1);
	fclose(f1);
	return bmp;
}

Bitmap *bmp_new(int h,int w){
	int i;
	Bitmap *bmp=NULL;
	bmp=(Bitmap*)malloc(sizeof(Bitmap));
	bmp->fh.Type[0]='B';
	bmp->fh.Type[1]='M';
	bmp->ih.Size=40;
	bmp->fh.Reserved1=0;
	bmp->fh.Reserved2=0;
	bmp->fh.OffBits=bmp->ih.Size+14;
	bmp->ih.Width=w;
	bmp->ih.Height=h;
	bmp->ih.Planes=1;
	bmp->ih.BitCount=24;
	bmp->ih.Compression=0;
	bmp->ih.XPelsPerMeter=2860;
	bmp->ih.YPelsPerMeter=2860;
	bmp->ih.ClrUsed=0;
	bmp->ih.ClrImportant=0;
	bmp->ih.SizeImage=(bmp->ih.BitCount/8)*w*h;
	bmp->fh.Size=bmp->ih.SizeImage+bmp->fh.OffBits;

	bmp->data=(unsigned char *)malloc(256);
	///////se llena la paleta de colores
	for (int i = 0; i <256; i++)
    {
        bmp->data[i]=250;
    }

	bmp->data=(unsigned char *)malloc(bmp->ih.SizeImage);
	for(i=0;i<bmp->ih.SizeImage;i++)
		bmp->data[i]=255;
	bmp->img=(unsigned char**)malloc(h*sizeof(unsigned char*));
	for(i=h-1;i>=0;i--)
		bmp->img[i]=&bmp->data[(h-i-1)*w];
	return bmp;
}

void bmp_save(Bitmap *bmp,char *file){
	FILE *f1;
	f1=fopen(file,"wb");
	if(f1==NULL){
		printf("\nNo se puede abrir el archivo: %s",file);
		getch();
		exit(-1);
	}
	fwrite(bmp->fh.Type,1,2,f1);
	fwrite(&bmp->fh.Size,4,1,f1);
	fwrite(&bmp->fh.Reserved1,2,1,f1);
	fwrite(&bmp->fh.Reserved2,2,1,f1);
	fwrite(&bmp->fh.OffBits,4,1,f1);
	fwrite(&bmp->ih.Size,4,1,f1);
	fwrite(&bmp->ih.Width,4,1,f1);
	fwrite(&bmp->ih.Height,4,1,f1);
	fwrite(&bmp->ih.Planes,2,1,f1);
	fwrite(&bmp->ih.BitCount,2,1,f1);
	fwrite(&bmp->ih.Compression,4,1,f1);
	fwrite(&bmp->ih.SizeImage,4,1,f1);
	fwrite(&bmp->ih.XPelsPerMeter,4,1,f1);
	fwrite(&bmp->ih.YPelsPerMeter,4,1,f1);
	fwrite(&bmp->ih.ClrUsed,4,1,f1);
	fwrite(&bmp->ih.ClrImportant,4,1,f1);
	fwrite(bmp->data,1,bmp->ih.SizeImage,f1);
	fclose(f1);
}

void bmp_free(Bitmap *bmp){
	free(bmp->data);
	free(bmp->img);
}

void bmp_point(Bitmap *bmp,int x,int y){
	int x1;
	if(x>=0 && y>=0 && x<bmp->ih.Width && y<bmp->ih.Height){
		x1=x;
		bmp->img[y][x1++]=0;
		//bmp->img[y][x1++]=0;
		//bmp->img[y][x1]=0;

	}
}


double fline(double x, float m){
	double y;
	y=x/(1-x-(x*x));
	return y;
}

void negative(Bitmap *bmp){
	int i;
	for(i=0;i<bmp->ih.SizeImage;i++){
		bmp->data[i]=255-bmp->data[i];
	}
}
void red(Bitmap*bmp){
int i;
for(i=0;i<bmp->ih.SizeImage;i++)
{
	bmp->data[i]=200;
	//bmp->data[i+1]=0;
}
}

void allcolors(Bitmap *bmp){
	int i;
	for(i=0; i<bmp->ih.SizeImage;i++)
	{
		bmp->data[i]=200;
		//bmp->data[i+1]=180;
		//bmp->data[i+2]=30;
	}
}

double f(double x){
	double y;
	y=x/(1-x-(x*x));
	return y;
}

void graph(Bitmap *bmp){
	double x,y,e=300;
	int i,j;
	for (i = 0, x = -2*M_PI; i < bmp->ih.Width; i++,x+=4*M_PI/bmp->ih.Width) {
		y=f(x);
		j=bmp->ih.Height/2-y*e;
		bmp_point(bmp,i,j);
		
	}		

}


int main(){
	Bitmap *bmp,*bmp2;
	char file[]="d.bmp";
	char file2[]="lena.bmp";
	
	bmp=bmp_new(512,1024);
	bmp_print(bmp);
	//red(bmp);
	allcolors(bmp);
	graph(bmp);


	bmp_save(bmp,file);
	bmp_free(bmp);

	printf("Archivo 2\n");
	bmp2=bmp_open(file2);
	
	bmp_print(bmp2);

	bmp_save(bmp2,file2);
	//bmp_free(bmp2);

	getch();
	return 0;
}