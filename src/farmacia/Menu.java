package farmacia;		

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import farmacia.controller.ProdutoController;
import farmacia.model.Medicamento;
import farmacia.model.Produto;


		public class Menu {

			public static void main(String[] args) {
				
				Scanner leia = new Scanner(System.in);
				
				ProdutoController produtos = new ProdutoController();
				
				int opcao, id, tipo;
				String nome, generico;
				float preco;

				while (true) {

					System.out.println("*****************************************************");
					System.out.println("                                                     ");
					System.out.println("                FARMÁCIA BEM ESTAR                   ");
					System.out.println("                                                     ");
					System.out.println("*****************************************************");
					System.out.println("                                                     ");
					System.out.println("            1 - Criar Produto                        ");
					System.out.println("            2 - Listar todos os Produtos             ");
					System.out.println("            3 - Buscar Produto por Numero            ");
					System.out.println("            4 - Atualizar Dados de um Produto        ");
					System.out.println("            5 - Apagar Produto                       ");
					System.out.println("            6 - Sair                                 ");
					System.out.println("                                                     ");
					System.out.println("*****************************************************");
					System.out.println("Entre com a opção desejada:                          ");
					System.out.println("                                                     ");

					try {
						opcao = leia.nextInt();
					}catch(InputMismatchException e) {
						System.out.println("Digite valores inteiros!");
						leia.nextLine();
						opcao = 0;
					}

					if (opcao == 6) {
						System.out.println("\nFarmácia Bem Estar - Remédio Barato é aqui!");
						sobre();
						leia.close();
						System.exit(0);
					}

					switch (opcao) {
						case 1:
							System.out.println("Criar Produto\n\n");
						
						
							System.out.println("Digite o nome do Produto: ");
							leia.skip("\\R");
							nome = leia.nextLine();
							
							//System.out.println("Digite o tipo do Produto (1 - Medicamentos): ");
							tipo = 1;
							
							System.out.println("Digite o Preço do Produto: ");
							preco = leia.nextFloat();
							
							System.out.println("Digite o nome Genérico: ");
							leia.skip("\\R");
							generico = leia.nextLine();

							produtos.cadastrar(new Medicamento(produtos.gerarNumero(), nome, tipo, preco, generico));
							
							keyPress();
		                    break;
						case 2:
							System.out.println("Listar todos os Produtos\n\n");

							produtos.listarTodas();
							
							keyPress();
							break;
						case 3:
							System.out.println("Consultar dados de um Produto - por id\n\n");

							System.out.println("Digite o Id do Produto: ");
							id = leia.nextInt();
							
							produtos.procurarPorNumero(id);
							
							keyPress();
							break;
						case 4:
							System.out.println("Atualizar dados de um Produto\n\n");

							System.out.println("Digite o Id do Produto: ");
							id = leia.nextInt();
							
							Optional<Produto> produto = produtos.buscarNaCollection(id);
							
							if(produto.isPresent()) {
								
								System.out.println("Digite o nome do Produto: ");
								leia.skip("\\R");
								nome = leia.nextLine();
								
								tipo = produto.get().getTipo();
								
								System.out.println("Digite o Preço do Produto: ");
								preco = leia.nextFloat();
								
								System.out.println("Digite o nome Genérico: ");
								leia.skip("\\R");
								generico = leia.nextLine();

								produtos.atualizar(new Medicamento(id, nome, tipo, preco, generico));
								
								
							}else
								System.out.println("O Produto não foi encontrado!");
							
							keyPress();
							break;
						case 5:
							System.out.println("Apagar Produto\n\n");

							System.out.println("Digite o Id do Produto: ");
							id = leia.nextInt();
							
							produtos.deletar(id);
							
							keyPress();
							break;
						default:
							System.out.println("\nOpção Inválida!\n" );
							break;
					}
				}

			}

			public static void sobre() {
				System.out.println("\n*********************************************************");
				System.out.println("Projeto Desenvolvido por: ");
				System.out.println("Generation Brasil - generation@generation.org");
				System.out.println("github.com/conteudoGeneration");
				System.out.println("*********************************************************");
			}
		
			public static void keyPress() {
				
				try {
					
					System.out.println("\n\nPressione a tecla Enter para continuar...");
					System.in.read();
					
				}catch(IOException e){
					
					System.out.println("Você pressionou uma tecla inválida!");
					
				}
			}
	}


