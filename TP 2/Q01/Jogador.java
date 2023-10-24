import java.util.*;
import java.io.*; 

class Jogador
{
    // Atributos privados
        private int id;
        private int altura;
        private int peso;
        private int anoNascimento;
        private String nome;
        private String universidade;
        private String cidadeNascimento;
        private String estadoNascimento;

    // Construtores get de todos os int
        public int getId ()
        {
            return id;
        }

        public int getAltura ()
        {
            return altura;
        }

        public int getPeso ()
        {
            return peso;
        }

        public int getAnoNascimento ()
        {
            return anoNascimento;
        }

    // Construtores get de todos os String
        public String getNome ()
        {
            return nome;
        }

        public String getUniversidade ()
        {
            return universidade;
        }

        public String getCidadeNascimento ()
        {
            return cidadeNascimento;
        }

        public String getEstadoNascimento ()
        {
            return estadoNascimento;
        }

    // Construtores set de todos os int
        public void setId (int id)
        {
            this.id = id;
        }

        public void setAltura (int altura)
        {
            this.altura = altura;
        }

        public void setPeso (int peso)
        {
            this.peso = peso;
        }

        public void setAnoNascimento (int anoNascimento)
        {
            this.anoNascimento = anoNascimento;
        }

    // Construtores set de todos os String
        public void setNome (String nome)
        {
            this.nome = nome;
        }

        public void setUniversidade (String universidade)
        {
            this.universidade = universidade;
        }

        public void setCidadeNascimento (String cidadeNascimento)
        {
            this.cidadeNascimento = cidadeNascimento;
        }

        public void setEstadoNascimento (String estadoNascimento)
        {
            this.estadoNascimento = estadoNascimento;
        }

    // Construtor clone
        public Jogador clone ()
        {
            Jogador jogadorClone = new Jogador();

            // Clonando todos os int
                jogadorClone.setId(this.id);
                jogadorClone.setAltura(this.altura);
                jogadorClone.setPeso(this.peso);
                jogadorClone.setAnoNascimento(this.anoNascimento);
            
            // Clonando todos os String
                jogadorClone.setNome(this.nome);
                jogadorClone.setUniversidade(this.universidade);
                jogadorClone.setCidadeNascimento(this.cidadeNascimento);
                jogadorClone.setEstadoNascimento(this.estadoNascimento);

            return jogadorClone;
        }

    // Construtor imprimir
        public void imprimir ()
        {
            System.out.println("[" + this.id + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## " + this.anoNascimento +
                                 " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## " + this.estadoNascimento + "]");
        }

    // Construtor ler
        public void ler (int id)
        {
            try
            {
                File arquivo = new File("/tmp/players.csv");
                Scanner scanner = new Scanner(arquivo);

                // Ignorar a primeira linha
                    if (scanner.hasNextLine()) 
                    {
                        scanner.nextLine();
                    }

                while (scanner.hasNextLine())
                {
                    String linha = scanner.nextLine();
                    //System.out.println("Linha do arquivo: " + linha);
                    List<String> partesSeparadas = separarLinha(linha);

                    int jogadorId = Integer.parseInt(partesSeparadas.get(0));

                    if (jogadorId == id)
                    {
                        preencherAtributos(partesSeparadas);
                        imprimir();
                        break;
                    }
                }

                scanner.close();
            }catch (FileNotFoundException e) 
            {
                System.err.println("Arquivo players.csv não encontrado.");
            }
        }

    // Construtor para separar a linha em partes
        public List<String> separarLinha (String linha)
        {
            String[] partes = linha.split(",");
            List<String> partesSeparadas = new ArrayList<>();

            for (String parte : partes)
            {
                if (parte.isEmpty())
                {
                    partesSeparadas.add ("nao informado");
                }

                else
                {
                    partesSeparadas.add (parte);
                }
            }

            return partesSeparadas;
        }

    // Construtor para atribuir valores aos atributos de acordo com a linha do arquivo
        public void preencherAtributos(List<String> partesSeparadas) 
        {
            this.id = -1;  // Valor padrão para o ID
            this.nome = "nao informado";  // Valor padrão para o nome
            this.altura = -1;  // Valor padrão para a altura
            this.peso = -1;  // Valor padrão para o peso
            this.universidade = "nao informado";  // Valor padrão para a universidade
            this.anoNascimento = -1;  // Valor padrão para o ano de nascimento
            this.cidadeNascimento = "nao informado";  // Valor padrão para a cidade de nascimento
            this.estadoNascimento = "nao informado";  // Valor padrão para o estado de nascimento

            if (partesSeparadas.size() >= 1)
            {
                this.id = Integer.parseInt(partesSeparadas.get(0));
            }

            if (partesSeparadas.size() >= 2)
            {
                this.nome = partesSeparadas.get(1);
            }

            if (partesSeparadas.size() >= 3)
            {
                this.altura = Integer.parseInt(partesSeparadas.get(2));
            }

            if (partesSeparadas.size() >= 4)
            {
                this.peso = Integer.parseInt(partesSeparadas.get(3));
            }

            if (partesSeparadas.size() >= 5)
            {
                this.universidade = partesSeparadas.get(4);
            }

            if (partesSeparadas.size() >= 6)
            {
                this.anoNascimento = Integer.parseInt(partesSeparadas.get(5));
            }

            if (partesSeparadas.size() >= 7)
            {
                this.cidadeNascimento = partesSeparadas.get(6);
            }

            if (partesSeparadas.size() >= 8)
            {
                this.estadoNascimento = partesSeparadas.get(7);
            }
        }

    // Método main para fazer o código funcionar
        public static void main(String[] args) 
        {
            Scanner scanner = new Scanner(System.in);
            Jogador jogador = new Jogador();
            
            while (true) 
            {
                String input = scanner.next();
    
                if (input.equals("FIM")) 
                {
                    break;
                }
    
                try 
                {
                    int id = Integer.parseInt(input);
                    jogador.ler(id);
                } catch (NumberFormatException e) {
                    System.err.println("ID inválido. Digite 'FIM' para encerrar.");
                }
            }

            scanner.close();
        }
}