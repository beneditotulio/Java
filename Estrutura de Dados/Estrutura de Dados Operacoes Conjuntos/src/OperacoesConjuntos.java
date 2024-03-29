import java.util.Arrays;

public class OperacoesConjuntos {

    // Retorna a união dos conjuntos A e B (elementos únicos em ambos os conjuntos)
    public static int[] uniao(int[] conjuntoA, int[] conjuntoB) {
        // Cria um array temporário com tamanho suficiente para armazenar todos os elementos de ambos os conjuntos
        int[] temp = new int[conjuntoA.length + conjuntoB.length];
        int tamanho = 0;
        // Adiciona cada elemento do conjunto A ao array temporário, se ele ainda não estiver presente
        for (int elemento : conjuntoA) {
            if (!contem(temp, elemento, tamanho)) {
                temp[tamanho++] = elemento;
            }
        }
        // Faz o mesmo para o conjunto B
        for (int elemento : conjuntoB) {
            if (!contem(temp, elemento, tamanho)) {
                temp[tamanho++] = elemento;
            }
        }
        // Retorna uma cópia do array temporário, mas apenas até o índice onde os elementos foram adicionados
        return Arrays.copyOf(temp, tamanho);
    }

    // Retorna a interseção dos conjuntos A e B (elementos presentes em ambos os conjuntos)
    public static int[] interseccao(int[] conjuntoA, int[] conjuntoB) {
        // Cria um array temporário com tamanho suficiente para armazenar o menor dos dois conjuntos
        int[] temp = new int[Math.min(conjuntoA.length, conjuntoB.length)];
        int tamanho = 0;
        // Para cada elemento em A, verifica se o elemento também está em B e se ainda não foi adicionado ao array temporário
        for (int elementoA : conjuntoA) {
            for (int elementoB : conjuntoB) {
                if (elementoA == elementoB && !contem(temp, elementoA, tamanho)) {
                    // Se o elemento estiver em ambos os conjuntos e ainda não estiver no array temporário, adiciona-o
                    temp[tamanho++] = elementoA;
                    break;
                }
            }
        }
        // Retorna uma cópia do array temporário, mas apenas até o índice onde os elementos foram adicionados
        return Arrays.copyOf(temp, tamanho);
    }

    // Retorna a diferença entre os conjuntos A e B (elementos que estão em A, mas não em B)
    public static int[] diferenca(int[] conjuntoA, int[] conjuntoB) {
        // Cria um array temporário com tamanho suficiente para armazenar todos os elementos do conjunto A
        int[] temp = new int[conjuntoA.length];
        int tamanho = 0;
        // Para cada elemento em A, verifica se o elemento não está em B
        for (int elementoA : conjuntoA) {
            if (!contem(conjuntoB, elementoA)) {
                // Se o elemento estiver em A mas não em B, adiciona-o ao array temporário
                temp[tamanho++] = elementoA;
            }
        }
        // Retorna uma cópia do array temporário, mas apenas até o índice onde os elementos foram adicionados
        return Arrays.copyOf(temp, tamanho);
    }

    // Retorna o complemento do conjunto em relação ao universo especificado
    public static int[] complemento(int[] conjunto, int[] universo) {
        // O complemento de um conjunto em relação a um universo é a diferença entre o universo e o conjunto
        return diferenca(universo, conjunto);
    }

    // Retorna o produto cartesiano dos conjuntos A e B
    public static int[][] produtoCartesiano(int[] conjuntoA, int[] conjuntoB) {
        // O produto cartesiano de dois conjuntos é um conjunto de pares ordenados onde o primeiro elemento do par é de A e o segundo é de B
        int[][] resultado = new int[conjuntoA.length * conjuntoB.length][2];
        int pos = 0;
        for (int elementoA : conjuntoA) {
            for (int elementoB : conjuntoB) {
                resultado[pos][0] = elementoA;
                resultado[pos][1] = elementoB;
                pos++;
            }
        }
        return resultado;
    }

    // Verifica se o conjunto A é um subconjunto do conjunto B
    public static boolean ehSubconjunto(int[] conjuntoA, int[] conjuntoB) {
        // Um conjunto A é um subconjunto de um conjunto B se todos os elementos de A também estão em B
        for (int elemento : conjuntoA) {
            if (!contem(conjuntoB, elemento)) {
                return false;
            }
        }
        return true;
    }

    // Retorna o conjunto das partes do conjunto especificado
    public static int[][] conjuntoDasPartes(int[] conjunto) {
        // O conjunto das partes de um conjunto é o conjunto de todos os subconjuntos possíveis
        int numSubconjuntos = (int) Math.pow(2, conjunto.length);
        int[][] partes = new int[numSubconjuntos][];
        for (int i = 0; i < numSubconjuntos; i++) {
            int tamanhoSubconjunto = Integer.bitCount(i);
            partes[i] = new int[tamanhoSubconjunto];
            for (int j = 0, pos = 0; j < conjunto.length; j++) {
                if ((i & (1 << j)) != 0) {
                    partes[i][pos++] = conjunto[j];
                }
            }
        }
        return partes;
    }

    // Retorna a diferença simétrica entre os conjuntos A e B
    public static int[] diferencaSimetrica(int[] conjuntoA, int[] conjuntoB) {
        // A diferença simétrica entre dois conjuntos é a união das diferenças dos conjuntos
        int[] difA = diferenca(conjuntoA, conjuntoB);
        int[] difB = diferenca(conjuntoB, conjuntoA);
        return uniao(difA, difB);
    }

    // Método auxiliar para verificar se um elemento está contido em um array até um determinado índice
    private static boolean contem(int[] array, int elemento, int ateIndice) {
        // Verifica se um elemento está presente em um array até um determinado índice
        for (int i = 0; i < ateIndice; i++) {
            if (array[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para verificar se um elemento está contido em um array
    private static boolean contem(int[] array, int elemento) {
        // Verifica se um elemento está presente em um array
        for (int valor : array) {
            if (valor == elemento) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        // Definindo alguns conjuntos de exemplo
        int[] conjuntoA = {1, 2, 3, 4};
        int[] conjuntoB = {3, 4, 5, 6};
        int[] universo = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Teste de união
        System.out.println("União: " + Arrays.toString(uniao(conjuntoA, conjuntoB)));

        // Teste de interseção
        System.out.println("Interseção: " + Arrays.toString(interseccao(conjuntoA, conjuntoB)));

        // Teste de diferença
        System.out.println("Diferença (A - B): " + Arrays.toString(diferenca(conjuntoA, conjuntoB)));

        // Teste de complemento
        System.out.println("Complemento de A no universo: " + Arrays.toString(complemento(conjuntoA, universo)));

        // Teste de produto cartesiano
        System.out.println("Produto Cartesiano (A x B): ");
        int[][] produto = produtoCartesiano(conjuntoA, conjuntoB);
        for (int[] par : produto) {
            System.out.println(Arrays.toString(par));
        }

        // Teste de subconjunto
        System.out.println("Conjunto A é subconjunto de B? " + ehSubconjunto(conjuntoA, conjuntoB));

        // Teste de conjunto das partes
        System.out.println("Conjunto das Partes de A: ");
        int[][] partes = conjuntoDasPartes(conjuntoA);
        for (int[] subconjunto : partes) {
            System.out.println(Arrays.toString(subconjunto));
        }

        // Teste de diferença simétrica
        System.out.println("Diferença Simétrica entre A e B: " + Arrays.toString(diferencaSimetrica(conjuntoA, conjuntoB)));
    }

}
