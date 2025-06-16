package util;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Tabela {

    public JTable criarTabela(JPanel jpn, Object[] largura, Object[] pos, Object[] col) throws NullPointerException {

        JTable tabela = new JTable(new DefaultTableModel());
        tabela.setVisible(true);

        DefaultTableModel modeloTabela = (DefaultTableModel) tabela.getModel();

        tabela.setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane jsp = new JScrollPane(tabela);
        tabela.setRowHeight(19);
        jsp.setBounds(10, 50, 703, 100);
        jsp.setVisible(true);
        jpn.add(jsp);

        // Habilita ordenação ao clicar no título da coluna
        RowSorter<TableModel> sorter = new TableRowSorter<>(modeloTabela);
        tabela.setRowSorter(sorter);

        // Adiciona as colunas no modelo da tabela
        for (Object colNome : col) {
            modeloTabela.addColumn(colNome);
        }

        // Renderizadores para alinhamento
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);

        // Configuração das colunas: largura e alinhamento
        for (int i = 0; i < largura.length; i++) {
            // Definindo a largura da coluna
            tabela.getColumnModel().getColumn(i).setMaxWidth(
                    Integer.parseInt(largura[i].toString())
            );

            // Definindo o alinhamento
            TableCellRenderer alinhamento;

            if (pos[i].equals("centro")) {
                alinhamento = centro;
            } else if (pos[i].equals("direita")) {
                alinhamento = direita;
            } else if (pos[i].equals("esquerda")) {
                alinhamento = esquerda;
            } else {
                alinhamento = centro; // padrão, se for inválido
            }

            tabela.getColumnModel().getColumn(i).setCellRenderer(alinhamento);
        }

        return tabela;
    }
}
