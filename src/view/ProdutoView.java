package view;

import dao.ProdutoDAO;
import model.Produto;
import table.ProdutoTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProdutoView extends JFrame{

    private JTextField tfCodigo;
    private JTextField tfDescricao;
    private JTextField tfPreco;
    private JButton btLimpar;
    private JTable tbProduto;
    private JTextField tfPesquisarDescricao;
    private JButton btExcluir;
    private JButton btSalvar;
    private JPanel Produto;

    Produto pro = new Produto();
    ProdutoDAO prd = new ProdutoDAO();
    @SuppressWarnings("unchecked")


    public ProdutoView(){
        setVisible(true);
        setSize(1280,500);
        setContentPane(Produto);
        setLocationRelativeTo(null);
        tbProduto.setModel(new ProdutoTableModel(new ProdutoDAO().listarTodos()));
        btExcluir.setEnabled(false);

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if (tfDescricao.getText().equals("") || tfPreco.getText().equals("")){
                // Campos em Branco
                JOptionPane.showMessageDialog(null,"Campos em Branco","Produto - Aviso",JOptionPane.WARNING_MESSAGE);

            }else {
                if (tfCodigo.getText().equals("")){
                    pro.setDescricao_produto(tfDescricao.getText());
                    pro.setPreco_produto(Double.parseDouble(tfPreco.getText()));
                    prd.inserir(pro);
                }else{
                    // Altera
                    pro.setDescricao_produto(tfDescricao.getText());
                    pro.setPreco_produto(Double.parseDouble(tfPreco.getText()));
                    pro.setCodigo_produto(Integer.parseInt(tfCodigo.getText()));
                    prd.alterar(pro);
                }

            }
                tbProduto.setModel(new ProdutoTableModel(new ProdutoDAO().listarTodos()));
                tfCodigo.setText("");
                tfDescricao.setText("");
                tfPreco.setText("");
                tfPesquisarDescricao.setText("");
                btExcluir.setEnabled(false);





            }
        });
        tbProduto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
             tfCodigo.setText(tbProduto.getValueAt(tbProduto.getSelectedRow(),ProdutoTableModel.COL_CODIGO_PRODUTO).toString());
             tfDescricao.setText(tbProduto.getValueAt(tbProduto.getSelectedRow(),ProdutoTableModel.COL_DESCRICAO_PRODUTO).toString());
             tfPreco.setText(tbProduto.getValueAt(tbProduto.getSelectedRow(),ProdutoTableModel.COL_PRECO_PRODUTO).toString());
             btExcluir.setEnabled(true);
            }
        });
        btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbProduto.setModel(new ProdutoTableModel(new ProdutoDAO().listarTodos()));
                tfCodigo.setText("");
                tfDescricao.setText("");
                tfPreco.setText("");
                tfPesquisarDescricao.setText("");
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                int escolha = JOptionPane.showConfirmDialog(null,"Quer Excluir ?"," Produto - Excluir",JOptionPane.YES_NO_OPTION);
                if(escolha == 0 ){
                    int codigo = Integer.parseInt(tfCodigo.getText());
                    prd.delete(codigo);
                    tbProduto.setModel(new ProdutoTableModel(new ProdutoDAO().listarTodos()));
                    tfCodigo.setText("");
                    tfDescricao.setText("");
                    tfPreco.setText("");
                    tfPesquisarDescricao.setText("");
                    btExcluir.setEnabled(false);
                }






            }
        });
        tfPesquisarDescricao.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

              String search = tfPesquisarDescricao.getText();


                  tbProduto.setModel(new ProdutoTableModel(new ProdutoDAO().listarTodosDescricao(search)));

            }
        });

    }



}
