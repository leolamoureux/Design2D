package design2d;

import java.util.List;
import javax.swing.JLabel;

public class PlayerDrawing implements java.io.Serializable {

    protected String nameDraw;
    protected List<JLabel> draw;

    public PlayerDrawing() {
    }

    public String getNameDraw() {
        return nameDraw;
    }

    public void setNameDraw(String nameDraw) {
        this.nameDraw = nameDraw;
    }

    public List<JLabel> getDraw() {
        return draw;
    }

    public void setDraw(List<JLabel> draw) {
        this.draw = draw;
    }

	@Override
	public String toString() {
		return "PlayerDrawing [nameDraw=" + nameDraw + "]";
	}
    

    
}
