package dominio;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * La clase Fondo extiende JPanel y se utiliza para mostrar un fondo de imagen
 * en un panel. Permite dibujar un fondo de imagen en el panel utilizando la
 * clase Graphics.
 *
 * @author Equipo 5
 */
public class Fondo extends JPanel {

    /**
     * Dibuja el fondo de imagen en el panel utilizando el objeto Graphics.
     *
     * @param g el objeto Graphics utilizado para dibujar el fondo
     */
    public void fondo(Graphics g) {
        Image fondo = new ImageIcon(getClass().getResource("/fondo.png")).getImage();
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Llama al método paintComponent de la superclase para pintar el
     * componente. Luego dibuja el fondo de imagen en el panel.
     *
     * @param g el objeto Graphics utilizado para pintar el componente
     */
    protected void pintar(Graphics g) {
        super.paintComponent(g);
        fondo(g);
    }
}
