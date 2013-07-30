# What is it?
flash4libgdx, as it's name says, is a flash animation package for the libgdx game engine.

# How to use?
It's poorly documented...maybe you can read the examples.

# Concepts

## Viewport

## Window

## Viewport center

## Canvas

## Transformation Point

In the FAnimation's construction function, you need to specify a Viewport area, or a Viewport center point at least. 
Get a KFrame，when you call KFrame.draw, you get a window specify by (x, y, w, h). All objects in the viewport area is scaled to be drawed in the window. In the case that you only specify the Viewport's center rather than a Viewport area, no scale would happen. things will be drawed based on Viewport center and Window center.

# How it's implemented?
I have a blog post for it [in chinese](http://www.zenlife.tk/flash-libgdx), but you'd better to read the f*cking source code.

# Limitate
1. filter transformation is not supported
2. chip in chip is not supported ... 
3. shape is not supported

I use it for my own project...not strictly tested. Maybe it's buggy, use it at your own risk!
