package Shape;

import GUI.Theme;
import Controller.Position;
import Resource.Images;
import javafx.scene.control.Button;

public class SingleBlock extends Button{
    public Theme theme;
    public Position position;

    SingleBlock(Position p){
        this.position = p;
    }

    public SingleBlock(){
        this.theme = Theme.theme1;
    }

    SingleBlock(Theme t, Position p){
        this.theme = t;
        this.position = p;
    }

    public void setTheme(String color, int themeId){
        this.setStyle(
                "-fx-background-image: url(" + Images.pics.get(color + "_" + themeId) + ");"
        );
    }
}
