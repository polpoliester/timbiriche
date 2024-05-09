package vista.juego;

import dominio.FormaJuego;
import dominio.Tablero;
import dominio.Marcador;
import dominio.Jugador;
import dominio.Sala;
import dominio.Linea;
import dominio.Cuadro;
import java.util.List;
import interfaces.IActualizable;
import cliente.Cliente;
import interfaces.ICliente;
import interfaces.IObservador;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import vista.jugador.FrmMenuPrincipal;

/**
 *
 * @author brawun
 */
public class FrmTablero extends javax.swing.JFrame implements IObservador, IActualizable {

    private Sala sala;
    private Jugador jugador;
    private ICliente sck;
    private PnlTablero pnlTablero;
    private int contador = 0;

    public FrmTablero(Marcador marcador, Jugador jugador) {
        this.jugador = jugador;
        initComponents();
        this.setTitle("Sala de juego - " + jugador.getNombre());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.sck = (ICliente) new Cliente(this.jugador, this);

        //Inicializar Sala
        Tablero tablero = new Tablero(marcador.getJugadores().size());
        this.sala = new Sala(marcador, tablero, marcador.getJugadores().size());
        System.out.println(this.sala.toString());

        establecerColores();
        establecerMarcador();
        establecerTablero();

        this.txtTurno.setText(this.sala.getMarcador().getJugadores().get(this.sala.getMarcador().getSiguiente()).getNombre());
    }

    private void establecerColores() {
        int index = this.sala.getMarcador().getJugadores().indexOf(this.jugador);
        this.sala.getMarcador().getJugadores().get(index).setColor(this.jugador.getColor());

        int indicador = 0;
        for (int i = 0; i < this.sala.getMarcador().getJugadores().size(); i++) {
            if (!this.sala.getMarcador().getJugadores().get(i).equals(this.jugador)) {
                this.sala.getMarcador().getJugadores().get(i).setColor(this.jugador.getPreferencia().getColores().get(indicador));
                indicador++;
            }
        }
    }

    private void establecerMarcador() {
        for (int i = 0; i < this.sala.getMarcador().getJugadores().size(); i++) {
            switch (i) {
                case 0:
                    pnlJugador1.add(new PnlJugador(this.sala.getMarcador().getJugadores().get(i)));
                    pnlJugador1.revalidate();
                    break;
                case 1:
                    pnlJugador2.add(new PnlJugador(this.sala.getMarcador().getJugadores().get(i)));
                    pnlJugador2.revalidate();
                    break;
                case 2:
                    pnlJugador3.add(new PnlJugador(this.sala.getMarcador().getJugadores().get(i)));
                    pnlJugador3.revalidate();
                    break;
                case 3:
                    pnlJugador4.add(new PnlJugador(this.sala.getMarcador().getJugadores().get(i)));
                    pnlJugador4.revalidate();
                    break;
                default:
                    break;
            }

            this.validate();

        }
    }

    private void actualizarMarcador(Marcador marcador) {
        for (int i = 0; i < this.sala.getMarcador().getJugadores().size(); i++) {
            switch (i) {
                case 0:
                    ((PnlJugador) pnlJugador1.getComponent(0)).setPuntaje(marcador.getJugadores().get(i).getPuntaje());
                    pnlJugador1.revalidate();
                    break;
                case 1:
                    ((PnlJugador) pnlJugador2.getComponent(0)).setPuntaje(marcador.getJugadores().get(i).getPuntaje());
                    pnlJugador2.revalidate();
                    break;
                case 2:
                    ((PnlJugador) pnlJugador3.getComponent(0)).setPuntaje(marcador.getJugadores().get(i).getPuntaje());
                    pnlJugador3.revalidate();
                    break;
                case 3:
                    ((PnlJugador) pnlJugador4.getComponent(0)).setPuntaje(marcador.getJugadores().get(i).getPuntaje());
                    pnlJugador4.revalidate();
                    break;
                default:
                    break;
            }

            this.validate();
        }
    }

    private void establecerTablero() {
        this.pnlTablero = new PnlTablero(this.sala.getTablero(), jugador);
        pnlTablero.agrega(this);
        pnlTablero.setSize(this.pnlFondoTablero.getSize());
        pnlTablero.setBorder(this.pnlFondoTablero.getBorder());
        this.pnlFondoTablero.add(pnlTablero);
        pnlTablero.estableceTablero();
        pnlTablero.setVisible(true);
        pnlTablero.repaint();
    }

    public static int obtenerNumeroRandom(int min, int max) {
        // Verifica que el rango sea válido
        if (min > max) {
            throw new IllegalArgumentException("El valor mínimo debe ser menor o igual al valor máximo.");
        }

        Random random = new Random();
        // La fórmula para obtener un número aleatorio en el rango [min, max] es: 
        // min + random.nextInt(max - min + 1)
        return min + random.nextInt(max - min + 1);
    }

    /**
     * Retorna la sala que se esta trabajando.
     *
     * @return
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * Establece la sala de trabajo.
     *
     * @param sala
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlFondoMarcador = new javax.swing.JPanel();
        pnlJugador1 = new javax.swing.JPanel();
        pnlJugador2 = new javax.swing.JPanel();
        pnlJugador3 = new javax.swing.JPanel();
        pnlJugador4 = new javax.swing.JPanel();
        lblTurnoDe = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        pnlFondoTablero = new javax.swing.JPanel();
        pnlFondoOpt = new javax.swing.JPanel();
        btnAbandonar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlFondoMarcador.setBackground(new java.awt.Color(255, 255, 255));
        pnlFondoMarcador.setPreferredSize(new java.awt.Dimension(300, 500));
        pnlFondoMarcador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlJugador1.setBackground(new java.awt.Color(237, 246, 250));
        pnlJugador1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 222, 249), 2, true));
        pnlJugador1.setMaximumSize(new java.awt.Dimension(278, 80));
        pnlJugador1.setMinimumSize(new java.awt.Dimension(278, 80));
        pnlJugador1.setName(""); // NOI18N

        javax.swing.GroupLayout pnlJugador1Layout = new javax.swing.GroupLayout(pnlJugador1);
        pnlJugador1.setLayout(pnlJugador1Layout);
        pnlJugador1Layout.setHorizontalGroup(
            pnlJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        pnlJugador1Layout.setVerticalGroup(
            pnlJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        pnlFondoMarcador.add(pnlJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 12, 260, -1));

        pnlJugador2.setBackground(new java.awt.Color(237, 246, 250));
        pnlJugador2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 222, 249), 2, true));
        pnlJugador2.setMaximumSize(new java.awt.Dimension(278, 80));
        pnlJugador2.setMinimumSize(new java.awt.Dimension(278, 80));

        javax.swing.GroupLayout pnlJugador2Layout = new javax.swing.GroupLayout(pnlJugador2);
        pnlJugador2.setLayout(pnlJugador2Layout);
        pnlJugador2Layout.setHorizontalGroup(
            pnlJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        pnlJugador2Layout.setVerticalGroup(
            pnlJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        pnlFondoMarcador.add(pnlJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 108, 260, -1));

        pnlJugador3.setBackground(new java.awt.Color(237, 246, 250));
        pnlJugador3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 222, 249), 2, true));
        pnlJugador3.setMaximumSize(new java.awt.Dimension(278, 80));
        pnlJugador3.setMinimumSize(new java.awt.Dimension(278, 80));

        javax.swing.GroupLayout pnlJugador3Layout = new javax.swing.GroupLayout(pnlJugador3);
        pnlJugador3.setLayout(pnlJugador3Layout);
        pnlJugador3Layout.setHorizontalGroup(
            pnlJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        pnlJugador3Layout.setVerticalGroup(
            pnlJugador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        pnlFondoMarcador.add(pnlJugador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 204, 260, -1));

        pnlJugador4.setBackground(new java.awt.Color(237, 246, 250));
        pnlJugador4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 222, 249), 2, true));
        pnlJugador4.setMaximumSize(new java.awt.Dimension(278, 80));
        pnlJugador4.setMinimumSize(new java.awt.Dimension(278, 80));

        javax.swing.GroupLayout pnlJugador4Layout = new javax.swing.GroupLayout(pnlJugador4);
        pnlJugador4.setLayout(pnlJugador4Layout);
        pnlJugador4Layout.setHorizontalGroup(
            pnlJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        pnlJugador4Layout.setVerticalGroup(
            pnlJugador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        pnlFondoMarcador.add(pnlJugador4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 300, 260, -1));

        lblTurnoDe.setFont(new java.awt.Font("Warung Kopi", 1, 24)); // NOI18N
        lblTurnoDe.setForeground(new java.awt.Color(255, 102, 204));
        lblTurnoDe.setText("Turno de:");
        pnlFondoMarcador.add(lblTurnoDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 402, -1, -1));

        txtTurno.setEditable(false);
        txtTurno.setBackground(new java.awt.Color(239, 250, 237));
        txtTurno.setFont(new java.awt.Font("Warung Kopi", 0, 18)); // NOI18N
        txtTurno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 249, 169), 2, true));
        pnlFondoMarcador.add(txtTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 443, 260, 59));

        jPanel1.add(pnlFondoMarcador, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 520));

        pnlFondoTablero.setBackground(new java.awt.Color(255, 255, 255));
        pnlFondoTablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 221, 245), 2));
        pnlFondoTablero.setMaximumSize(new java.awt.Dimension(650, 650));
        pnlFondoTablero.setMinimumSize(new java.awt.Dimension(650, 650));

        javax.swing.GroupLayout pnlFondoTableroLayout = new javax.swing.GroupLayout(pnlFondoTablero);
        pnlFondoTablero.setLayout(pnlFondoTableroLayout);
        pnlFondoTableroLayout.setHorizontalGroup(
            pnlFondoTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        pnlFondoTableroLayout.setVerticalGroup(
            pnlFondoTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        jPanel1.add(pnlFondoTablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        pnlFondoOpt.setBackground(new java.awt.Color(255, 255, 255));

        btnAbandonar.setBackground(new java.awt.Color(255, 153, 200));
        btnAbandonar.setFont(new java.awt.Font("Warung Kopi", 1, 18)); // NOI18N
        btnAbandonar.setForeground(new java.awt.Color(102, 0, 51));
        btnAbandonar.setText("Abandonar Partida");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFondoOptLayout = new javax.swing.GroupLayout(pnlFondoOpt);
        pnlFondoOpt.setLayout(pnlFondoOptLayout);
        pnlFondoOptLayout.setHorizontalGroup(
            pnlFondoOptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoOptLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnAbandonar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlFondoOptLayout.setVerticalGroup(
            pnlFondoOptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoOptLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnAbandonar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(pnlFondoOpt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo2.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere abandonar la partida?", "Abandonar partida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.sala.eliminarJugador(this.jugador);
            this.jugador.setPuntaje(0);
            this.dispose();
            new FrmMenuPrincipal(this.jugador).setVisible(true);
        }
    }//GEN-LAST:event_btnAbandonarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTurnoDe;
    private javax.swing.JPanel pnlFondoMarcador;
    private javax.swing.JPanel pnlFondoOpt;
    private javax.swing.JPanel pnlFondoTablero;
    private javax.swing.JPanel pnlJugador1;
    private javax.swing.JPanel pnlJugador2;
    private javax.swing.JPanel pnlJugador3;
    private javax.swing.JPanel pnlJugador4;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizarSocket(Object mensaje) {
        if (mensaje instanceof Marcador) {
            System.out.println("Actualizando marcador");
            Marcador marcador = (Marcador) mensaje;
            actualizarMarcador((Marcador) mensaje);
            
            this.sala.getMarcador().setSiguiente(this.sala.getMarcador().getSiguiente() + 1);
            if (this.sala.getMarcador().getSiguiente() == this.sala.getMarcador().getJugadores().size()) {
                this.sala.getMarcador().setSiguiente(0);
            }

            for (int i = 0; i < marcador.getJugadores().size(); i++) {
                if (marcador.getJugadores().indexOf(this.jugador) == marcador.getSiguiente()) {
                    this.pnlTablero.actualizaTurno(true);
                }
            }

            this.txtTurno.setText(this.sala.getMarcador().getJugadores().get(this.sala.getMarcador().getSiguiente()).getNombre());
        } else if (mensaje instanceof List) {
            List<FormaJuego> formas = (List<FormaJuego>) mensaje;

            for (int i = 0; i < formas.size(); i++) {
                for (Jugador jugador : this.sala.getMarcador().getJugadores()) {
                    if (jugador.equals(formas.get(i).getJugador())) {
                        formas.get(i).setJugador(jugador);
                        if (i == 0) {
                            this.pnlTablero.actualizaLineaTablero((Linea) formas.get(i));
                        } else {
                            this.pnlTablero.actualizaCuadroTablero((Cuadro) formas.get(i));
                        }
                    }
                }
            }

            int cantidadJugadores = this.sala.getMarcador().getJugadores().size();
            int siguiente = this.sala.getMarcador().getSiguiente();
            
            if (cantidadJugadores < siguiente) {
                this.sala.getMarcador().setSiguiente(0);
                pnlJugador2.revalidate();
            } else {
                this.sala.getMarcador().setSiguiente(this.sala.getMarcador().getSiguiente() + 1);
            }

            this.txtTurno.setText(this.sala.getMarcador().getJugadores().get(this.sala.getMarcador().getSiguiente()).getNombre());
        }
    }

    @Override
    public void actualiza(List<FormaJuego> movimiento
    ) {
        sck.enviarAlServidor(movimiento);
    }
}
