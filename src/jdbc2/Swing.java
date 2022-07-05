package jdbc2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Swing{
	public static void main(String[] args) {
		Produto produto = new Produto();
		produto.igualaID();
		JFrame janela = new JFrame("Registrar Produto"); 
		janela.setResizable(false);
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setSize(800, 600);
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);
		JLabel labelQuantidade = new JLabel("Quantidade: ");
		JLabel labelPreco = new JLabel("Preço: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelConsultaID = new JLabel("ID: ");
		labelQuantidade.setBounds(50, 40, 100, 20);
		labelPreco.setBounds(50, 80, 150, 20);
		labelNome.setBounds(50, 120, 100, 20);
		labelConsultaID.setBounds(50, 150, 100, 40);
		JTextField jTextQuantidade = new JTextField();
		JTextField jTextPreco = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextConsultaID = new JTextField();
		jTextQuantidade.setEnabled(true);
		jTextPreco.setEnabled(true);
		jTextNome.setEnabled(true);
		jTextConsultaID.setEnabled(false);
		jTextQuantidade.setBounds(180, 40, 50, 20);
		jTextPreco.setBounds(180, 80, 50, 20);
		jTextNome.setBounds(180, 120, 150, 20);
		jTextConsultaID.setBounds(180, 160, 150, 20);
		janela.add(labelQuantidade);
		janela.add(labelPreco);
		janela.add(labelNome);
		janela.add(labelConsultaID);
		janela.add(jTextQuantidade);
		janela.add(jTextPreco);
		janela.add(jTextNome);
		janela.add(jTextConsultaID);
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(360, 160, 100, 20);
		botaoConsultar.setEnabled(false);
		janela.add(botaoConsultar);
		JButton botaoRemocao = new JButton("Remover");
		botaoRemocao.setBounds(270, 200, 100, 20);
		botaoRemocao.setEnabled(false);
		janela.add(botaoRemocao);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(true);
		janela.add(botaoGravar);
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(380, 200, 100, 20);
		botaoAtualizar.setEnabled(false);
		janela.add(botaoAtualizar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(160, 200, 100, 20);
		botaoLimpar.setEnabled(true);
		janela.add(botaoLimpar);
		if(produto.checaTabela()) {
			botaoAtualizar.setEnabled(true);
			botaoRemocao.setEnabled(true);
			botaoConsultar.setEnabled(true);
			jTextConsultaID.setEnabled(true);
		}
		
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextConsultaID.getText());
					String nome;
					int quantidade=0;
					double preco=0;
					if (!produto.consultarProduto(id))
						nome = "";
					else {
						nome = produto.getNome();
						quantidade = produto.getQuantidade();
						preco = produto.getPreco();
						id = produto.getId();
					}
					jTextNome.setText(nome);
					jTextQuantidade.setText(String.valueOf(quantidade));
					jTextPreco.setText(String.valueOf(preco));
					jTextConsultaID.setText(String.valueOf(id));
					jTextConsultaID.setEnabled(false);
					jTextNome.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextQuantidade.setEnabled(false);
					jTextPreco.setEnabled(false);
					jTextNome.requestFocus();
					jTextConsultaID.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,
							"Preencha os campos corretamente!!");
				}
			}
		});
		
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantidade = Integer.parseInt(jTextQuantidade.getText());
				String nome = jTextNome.getText().trim(); // Retira os espaços em branco
				double preco = Double.parseDouble(jTextPreco.getText());
				if (nome.length()==0) {
					JOptionPane.showMessageDialog(janela, "Preencha o campo nome!");
					jTextNome.requestFocus();
				}
				else if(preco == 0) {
							JOptionPane.showMessageDialog(janela, "Preencha o campo preço!");
							jTextPreco.requestFocus();
				}
				if(!produto.cadastrarProduto(nome, quantidade, preco)) {
					JOptionPane.showMessageDialog(janela, "Erro no Cadastro!");
				}
				else {
					JOptionPane.showMessageDialog(janela, "Cadastro do produto realizado com sucesso!");
					botaoLimpar.setEnabled(true);
					botaoAtualizar.setEnabled(true);
					botaoRemocao.setEnabled(true);
					botaoConsultar.setEnabled(true);
				}
			}
		});
		
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(jTextConsultaID.getText());
				int quantidade = Integer.parseInt(jTextQuantidade.getText());
				String nome = jTextNome.getText().trim(); // Retira os espaços em branco
				double preco = Double.parseDouble(jTextPreco.getText());
				if (nome.length()==0) {
					JOptionPane.showMessageDialog(janela, "Preencha o campo nome!");
					jTextNome.requestFocus();
				}
				else if(preco == 0) {
							JOptionPane.showMessageDialog(janela, "Preencha o campo preço!");
							jTextPreco.requestFocus();
				}
				if(!produto.atualizarProduto(id, quantidade, preco, nome)) {
					JOptionPane.showMessageDialog(janela, "Erro na Atualização!");
				}
				else {
					JOptionPane.showMessageDialog(janela, "Atualização do produto realizado com sucesso!");
				}
			}
		});
		
		botaoRemocao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(jTextConsultaID.getText());
				if(!produto.consultarProduto(id)) {
					JOptionPane.showMessageDialog(janela, "Produto não existe ou não encontrado.");
				}
				else {
					if(produto.deletarProduto(id));
					JOptionPane.showMessageDialog(janela, "Produto removido!");
				}
				if(produto.checaTabela()) {
					botaoAtualizar.setEnabled(true);
					botaoRemocao.setEnabled(true);
					botaoConsultar.setEnabled(true);
					jTextConsultaID.setEnabled(true);
				}
				else {
					botaoAtualizar.setEnabled(false);
					botaoRemocao.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextConsultaID.setEnabled(false);
				}
		}
			
		});
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextQuantidade.setText(""); 
				jTextPreco.setText(""); 
				jTextNome.setText(""); 
				jTextConsultaID.setText("");
				jTextQuantidade.setEnabled(true);
				jTextPreco.setEnabled(true);
				jTextNome.setEnabled(true);
				jTextConsultaID.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(true);
			}
		});
		janela.setVisible(true); 
	}
}