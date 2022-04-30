package Shape;

public abstract class Shape{
    public SingleBlock[] blocks;
    private int velocity = 1;

    public abstract void leftRotate();

    public abstract void rightRotate();

    abstract SingleBlock[] getBlocks();

    public void speedUp(){
        this.velocity = 2;
    }

    public void speedDown(){
        this.velocity = 1;
    }

    public int getSize(){
        return this.blocks.length;
    }

    public int getIof(int i){
        return this.blocks[i].position.getI();
    }

    public int getJof(int i){
        return this.blocks[i].position.getJ();
    }

    public void moveDown(){
        for(SingleBlock block : this.blocks){
            block.position = block.position.down();
        }
    }

}
