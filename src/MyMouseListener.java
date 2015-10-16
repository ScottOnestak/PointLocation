import java.awt.event.*;
import javax.swing.*;

public class MyMouseListener implements MouseListener
{

    public void mouseClicked(MouseEvent e)
    {
        ((UnitSquare)(e.getSource())).addPoint(e);
        ((UnitSquare)(e.getSource())).checkPoints();

    }

    public void mouseEntered(MouseEvent e){return;}

    public void mouseExited(MouseEvent e){return;}

    public void mousePressed(MouseEvent e){return;}

    public void mouseReleased(MouseEvent e){return;}
}
