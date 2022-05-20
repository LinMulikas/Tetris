package Shape;

import GUI.Theme;
import Controller.Position;

import static Shape.Direction.*;

public class Shape_I extends Shape{
    Position p = new Position(  1, 3);

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
    public SingleBlock[] getBlocks(){
        return this.blocks;
    }

    @Override
    public SingleBlock[] rightRotatedBlocks(){
        Shape_I result = this.cloneShape();
        switch(result.state){
            case O:
                result.state = R;
                result.p.i -= 1;
                result.p.j += 2;
                result.extendBlocks();
                return result.blocks;
            case R:
                result.state = D;
                result.p.i += 2;
                result.p.j += 1;
                result.extendBlocks();
                return result.blocks;
            case D:
                result.state = L;
                result.p.i += 1;
                result.p.j -= 2;
                result.extendBlocks();
                return result.blocks;
            case L:
                result.state = O;
                result.p.i -= 2;
                result.p.j -= 1;
                result.extendBlocks();
                return result.blocks;
        }

        return null;
    }

    @Override
    public SingleBlock[] downBlocks(){
        SingleBlock[] ans = new SingleBlock[this.blocks.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = new SingleBlock();
        }

        for(int i = 0; i < ans.length; i++){
            ans[i].position = this.blocks[i].position.down();
        }
        return ans;
    }

    @Override
    public SingleBlock[] leftBlocks(){
        SingleBlock[] ans = new SingleBlock[this.blocks.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = new SingleBlock();
        }

        for(int i = 0; i < ans.length; i++){
            ans[i].position = this.blocks[i].position.left();
        }
        return ans;
    }

    @Override
    public void leftRotate(){
        switch(state){
            case O:
                this.state = L;
                this.p.i += 2;
                this.p.j += 1;
                this.extendBlocks();
                return;
            case R:
                this.state = O;
                this.p.i += 1;
                this.p.j -= 2;
                this.extendBlocks();
                return;
            case D:
                this.state = R;
                this.p.i -= 2;
                this.p.j -= 1;
                this.extendBlocks();
                break;
            case L:
                this.state = D;
                this.p.i -= 1;
                this.p.j += 2;
                this.extendBlocks();
        }
    }

    @Override
    public SingleBlock[] rightBlocks(){
        SingleBlock[] ans = new SingleBlock[this.blocks.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = new SingleBlock();
        }

        for(int i = 0; i < ans.length; i++){
            ans[i].position = this.blocks[i].position.right();
        }
        return ans;
    }

    public SingleBlock[] leftRotatedBlocks(){
        Shape_I result = this.cloneShape();
        switch(result.state){
            case O:
                result.state = L;
                result.p.i += 2;
                result.p.j += 1;
                result.extendBlocks();
                return result.blocks;
            case R:
                result.state = O;
                result.p.i += 1;
                result.p.j -= 2;
                result.extendBlocks();
                return result.blocks;
            case D:
                result.state = R;
                result.p.i -= 2;
                result.p.j -= 1;
                result.extendBlocks();
                return result.blocks;
            case L:
                result.state = D;
                result.p.i -= 1;
                result.p.j += 2;
                result.extendBlocks();
                return result.blocks;
        }
        return null;
    }

    // 克隆一个位置等关键信息相同的该形状
    public Shape_I cloneShape(){
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
    public void rightRotate(){
        switch(state){
            case O:
                this.state = R;
                this.p.i -= 1;
                this.p.j += 2;
                this.extendBlocks();
                return;
            case R:
                this.state = D;
                this.p.i += 2;
                this.p.j += 1;
                this.extendBlocks();
                return;
            case D:
                this.state = L;
                this.p.i += 1;
                this.p.j -= 2;
                this.extendBlocks();
                return;
            case L:
                this.state = O;
                this.p.i -= 2;
                this.p.j -= 1;
                this.extendBlocks();
        }
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
