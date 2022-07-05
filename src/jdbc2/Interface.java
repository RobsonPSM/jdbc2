package jdbc2;

import java.util.Scanner;


public class Interface {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Produto produto = new Produto();
		int opcao, quantidade, id, opcao1;
		String nome;
		Double preco;
		id=1;
		do {
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Consultar produto");
			System.out.println("3 - Atualizar estoque");
			System.out.println("4 - Encerrar");
			System.out.print("Entre com uma opção: ");
			opcao = Integer.parseInt(sc.nextLine());
			if (opcao < 1 || opcao > 4)
				System.out.println("Opção incorreta!");
			else {
				if (opcao != 4) {
					try {
						switch (opcao) {
						case 1:
							System.out.print("Entre com o nome do produto = ");
							nome = sc.nextLine();
							System.out.print("Entre com o quantidade dos produtos = ");
							quantidade = Integer.parseInt(sc.nextLine());
							System.out.print("Entre com o preço do produto = ");
							preco = Double.parseDouble(sc.nextLine());
							System.out.println("Id do Produto = " + id);
							id++;
							produto.cadastrarProduto(nome, quantidade, preco);
							break;
						case 2:
							System.out.println("Informe o ID do produto = ");	
							opcao1 = Integer.parseInt(sc.nextLine());
							if(!(produto.consultarProduto(opcao1)));
								System.out.println("Produto não encontrado");
							break;
						case 3:
							System.out.println("Informe o ID do produto: ");	
							opcao1 = Integer.parseInt(sc.nextLine());
							produto.consultarProduto(opcao1);
							System.out.println("Informe (nova) quantidade do produto = ");
							quantidade = Integer.parseInt(sc.nextLine());
							System.out.println("Informe (novo) preco do produto = ");
							preco = sc.nextDouble();
							System.out.println("Informe (novo) nome do produto = ");
							nome = sc.nextLine();
							produto.atualizarProduto(id,quantidade,preco,nome);
							break;
						}
					} catch (NumberFormatException erro) {
						System.out.println("Entre com o formato correto dos dados!");
					} catch (Exception erro) {
						System.out.println("Erro não identificado: " + erro.toString());
					}
				}
			}
		} while (opcao != 4);
		System.out.println("Programa encerrado!");
		sc.close();
	}
}

