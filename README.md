# Mandelbrot

This is a simple mandelbrot zooming program. The main class is me.jangroen.mandelbrot.Mandelbrot and in there the important values such as color resolution, starting position, starting scale and zooming factor per frame can be set.

### How it works

This creates a work stealing executor service that is responsible to render multiple frames in parallel. The paint method of the MandelbrotPane calls the repaint method on itself and every execution of paint gets the next frame from the queue or waits for it if necessary. This is not a good way to handle the repainting, but it was the easiest to implement to keep the complexity of the program low. Frame rate is limited to 30 frames per second.

The entire program relies on the MandelbrotNumber which is at the moment just a wrapper for the primitive java double. This has been done so that implementing double-double-precision is easier if it is needed.  