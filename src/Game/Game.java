package Game;

import Shape.Shape;
import Shape.SingleBlock;

public class Game{
    int[][] innerBlocks = new int[24][10];
    int[] countRows = new int[20];
    Shape currentShape;
    Shape nextShape;

    public void printAllBlocks(){
        for(int x = 0; x < 24; x++){
            for(int y = 0; y< 10; y++){
                System.out.printf("%d ", this.innerBlocks[x][y]);
            }
            System.out.println();
        }
    }

    public boolean isBoundary(Position position){
        int x = position.i;
        int y = position.j;
        return (x >= 0 && x<= 23) && (y >= 0 && y <= 9);
    }

    public boolean checkMove(Shape shape){
        int[][] area = this.innerBlocks;
        for(SingleBlock block : shape.blocks){
            if(area[block.position.down().getI()][block.position.down().getI()] == 1){
                return false;
            }
        }
        return true;
    }

    public void absorb(SingleBlock[] blocks){
        for(SingleBlock block : blocks){
            this.innerBlocks[block.position.i][block.position.j] = 1;
        }
    }

    public void killRow(){};

    private boolean checkBorn(Shape shape){
        int deltaX = 3;

        if("IShape".equals(shape.getClass().getName())){
            deltaX = 4;
        }

        for(int i = 0; i < shape.getSize(); i++){
            if(!isEmpty(shape.getIof(i) + deltaX, shape.getJof(i))){
                return false;
            }
        }
        return true;
    }

    private void bornShape(Shape shape){
        int deltaX = 3;

        if("IShape".equals(shape.getClass().getName())){
            deltaX = 4;
        }

        for(int i = 0; i < deltaX; i++){
            shape.moveDown();
        }
    }

    boolean isEmpty(int i, int j){
        return this.innerBlocks[i][j] == 0;
    }

    boolean isEmpty(Position position){
        return isEmpty(position.i, position.j);
    }
}
