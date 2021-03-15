# Paint Tools
#### _Android 3rd party library to make implementing Photo editor and painter more easier_

I made this library to make it easier for developers to implement a photo editor and painter, e.g. you can use it to make your user adit his photo before sending it in chatting app, or since it is opensource you can enhance it and build your own paint application. 



## ScreenShots
![ezgif com-gif-maker](https://user-images.githubusercontent.com/35175706/111139618-032a0a80-858a-11eb-8bd5-5c22dae3c3d3.gif)

![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/35175706/111139709-22289c80-858a-11eb-9fe1-cb76a5f127f9.gif)
.


## Installation
Add this to yor build.gradle(Project) file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Then add this impelementation to your build.gradle(Module) file
```
dependencies {
            	//for Edit tool
	        implementation 'com.github.mahmoudhesham009.PaintTools:EditTool:0.1.1'
	        //for Paint tool
	        implementation 'com.github.mahmoudhesham009.PaintTools:PaintTool:0.1.1'
	}
```

## How to use it
### PaintTool:
```
PaintTool.with(MainActivity.this)
                        .setToolBarColor(Color.GRAY)
                        .setToolBarTitle("Hello from painter")
                        .setBackgroundColor(Color.BLACK)
                        .show();
```
to get your paint result add thit to your activity:
```
@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case PaintTool.DRAW_RESULT:
                //your bitmap result
                Bitmap bmp = PaintTool.getDrawResult(data);
                imageView.setImageBitmap(bmp);
                break;
        }
    }
```

### EditTool:
```
EditTool.with(MainActivity.this, yourUri)
                        .setToolBarColor(Color.WHITE)
                        .setToolBarTitle("Hello from editor")
                        .show();
```
to get your edit result add thit to your activity:
```
@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case PaintTool.DRAW_RESULT:
                //your bitmap result
                Bitmap bmp = EditTool.getDrawResult(data);
                imageView.setImageBitmap(bmp);
                break;
        }
    }
```
