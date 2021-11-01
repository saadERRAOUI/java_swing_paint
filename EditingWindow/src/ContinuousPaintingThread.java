
class ContinuousPaintingThread extends Thread {
    //a Class that describes a thread responsible for painting continuously on a image
    private ImagePanel imagePanel;
    private boolean exit = false;

    public ContinuousPaintingThread(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    @Override
    public void run() {
        //while the exit flag isn't raised, paint continuously
        while (!exit) {
            imagePanel.paintQueue(false);
        }
    }

    public void setExit(boolean exit) {
        //a controler that stops the while loop when called
        this.exit = exit;
    }

}