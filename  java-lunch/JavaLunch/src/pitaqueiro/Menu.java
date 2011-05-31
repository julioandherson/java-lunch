/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Menu.java
 *
 * Created on 17/05/2011, 22:31:17
 */
package pitaqueiro;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas Albuqueruque | Graduando em Ciência da Computação - Universidade Federal de Campina Grande
 */
public class Menu extends javax.swing.JFrame {
    
    
    
    /** Creates new form Menu */
    public Menu() {
       
        initComponents();
    }
    
   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomePrograma = new javax.swing.JLabel();
        BotaoCadastrar = new javax.swing.JButton();
        BotaoExibirPerfil = new javax.swing.JButton();
        Bacessar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BotaoRanking = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setName("Menu"); // NOI18N

        NomePrograma.setFont(new java.awt.Font("Tahoma", 1, 14));
        NomePrograma.setText("Java Lunch™");
        NomePrograma.setBorder(new javax.swing.border.MatteBorder(null));

        BotaoCadastrar.setFont(new java.awt.Font("Tahoma", 1, 11));
        BotaoCadastrar.setText("Cadastrar");
        BotaoCadastrar.setToolTipText("Cadastra Usuario");
        BotaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCadastrarActionPerformed(evt);
            }
        });

        BotaoExibirPerfil.setFont(new java.awt.Font("Tahoma", 1, 11));
        BotaoExibirPerfil.setText("Exibir Perfil");
        BotaoExibirPerfil.setToolTipText("Seleciona e exibe dados de um determinado usuario");
        BotaoExibirPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoExibirPerfilActionPerformed(evt);
            }
        });

        Bacessar.setFont(new java.awt.Font("Tahoma", 1, 11));
        Bacessar.setText("Acessar");
        Bacessar.setToolTipText("Seleciona um usuario ja cadastrado e algoritmo para gerar recomendacoes baseadas no usuario selecionado");
        Bacessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BacessarActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:/Users/Lucas/Documents/NetBeansProjects/Pitaqueiro/src/Arquivos/logo1.gif"));

        BotaoRanking.setFont(new java.awt.Font("Tahoma", 1, 11));
        BotaoRanking.setText("Ranking");
        BotaoRanking.setToolTipText("Exibi uma lista dos melhores Restaurantes");
        BotaoRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRankingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                            .addComponent(BotaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoExibirPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Bacessar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NomePrograma)
                            .addComponent(BotaoRanking)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Bacessar, BotaoCadastrar, BotaoExibirPerfil, BotaoRanking});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NomePrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(BotaoCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotaoExibirPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bacessar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(BotaoRanking)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Bacessar, BotaoCadastrar, BotaoExibirPerfil, BotaoRanking});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoExibirPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoExibirPerfilActionPerformed
        new ExibirPerfil().show();
        this.dispose();
    }//GEN-LAST:event_BotaoExibirPerfilActionPerformed

    private void BacessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BacessarActionPerformed
        new Acessar().show();
        this.dispose();
    }//GEN-LAST:event_BacessarActionPerformed

    private void BotaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCadastrarActionPerformed
        new Cadastra2().show();
        this.dispose();
    }//GEN-LAST:event_BotaoCadastrarActionPerformed

    private void BotaoRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRankingActionPerformed
        new Ranking().show();
        this.dispose();
    }//GEN-LAST:event_BotaoRankingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bacessar;
    private javax.swing.JButton BotaoCadastrar;
    private javax.swing.JButton BotaoExibirPerfil;
    private javax.swing.JButton BotaoRanking;
    private javax.swing.JLabel NomePrograma;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}