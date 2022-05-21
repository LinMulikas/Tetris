package Shape;

import GUI.Theme;
import Controller.Position;

import static Shape.Direction.*;

public class Shape_I extends Shape{

    public Shape_I(){
        theme = Theme.theme1;
        blocks = new SingleBlock[4];
        for(int i = 0; i < 4; i ++){
            this.blocks[i] = new SingleBlock();
        }
    }

    @Override
    public void extendBlocks(){
        this.blocks[0].position = this.p;
        switch(this.state){
            case O:
                blocks[1].position = blocks[0].position.right();
                blocks[2].position = blocks[1].position.right();
                blocks[3].position = blocks[2].position.right();
                break;
            case L:
                blocks[1].position = blocks[0].position.up();
                blocks[2].position = blocks[1].position.up();
                blocks[3].position = blocks[2].position.up();
                break;
            case D:
                blocks[1].position = blocks[0].position.left();
                blocks[2].position = blocks[1].position.left();
                blocks[3].position = blocks[2].position.left();
                break;
            case R:
                blocks[1].position = blocks[0].position.down();
                blocks[2].position = blocks[1].position.down();
                blocks[3].position = blocks[2].position.down();
                break;
        }
    }

    @Override
    public Shape cloneShape(){
        Shape_I result = new Shape_I();
        result.p = new Position(this.p.i, this.p.j);
        result.state = this.state;
        result.blocks = new SingleBlock[this.blocks.length];
        for(int i = 0; i < result.blocks.length; i++){
            result.blocks[i] = new SingleBlock();
        }
        return result;
    }

    @Override
    public void setTheme(Theme theme){
        switch(theme){
            case theme1:
                for(SingleBlock block : blocks){
                    block.setTheme("Cyan", 1);
                }
                break;
            case theme2:
                for(SingleBlock block : blocks){
                    block.setTheme("Cyan", 2);
                }
                break;
            case theme3:
                for(SingleBlock block : blocks){
                    block.setTheme("Cyan", 3);
                }
                break;
        }
    }
}
