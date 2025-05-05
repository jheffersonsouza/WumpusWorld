package utils;

import core.entity.BaseEntity;
import core.world.World;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphicMock {


    public static void print(World world) {
        System.out.println(" ");
        String[][] asciiParsed = new String[world.getSize()][world.getSize()];
        for (int i = 0; i < world.getSize(); i++) {
            for (int j = 0; j < world.getSize(); j++) {
                ArrayList<BaseEntity> tile = world.WORLD[i][j];
                StringBuilder tS = new StringBuilder();
                tile.forEach(e -> {
                    tS.append(e.asString());
                           // .append("/");
                });
                //tS.deleteCharAt(tS.length() - 1);
                asciiParsed[i][j] = tS.toString();
            }
        }
        imprimirMatrizFormatada(asciiParsed);
        /**
         for (int i = 0; i < world.getSize(); i++) {
         StringBuilder header = new StringBuilder();
         StringBuilder body = new StringBuilder();
         for (int j = 0; j < world.getSize(); j++) {
         String tile = asciiParsed[i][j];
         for (int k = 0; k < tile.length(); k++) {
         header.append("-");
         }
         body.append("|").append(tile);


         }
         for (int j = 0; j < world.getSize() - 1; j++) {
         header.append("-");
         }
         body.append("|");
         System.out.println(header.toString());
         System.out.println(body.toString());
         System.out.println(header);
         }
         System.out.println(" ");
         **/
    }
    // Gerado pelo Claude 3.7 Sonnet - Usado so pra poupar tempo no ascii.
    // Nao li o que o codigo faz só funciona e to usando, mas pretendo fazer um a mão depois
    // ou expor isso como api webscocket e fazer um front, sei la, fazer ascii n é comigo.
    public static void imprimirMatrizFormatada(String[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            System.out.println("Matriz vazia!");
            return;
        }

        int linhas = matriz.length;
        int colunas = matriz[0].length;

        // 1. Calcular a largura necessária para cada coluna
        int[] largurasColunas = new int[colunas];
        for (int j = 0; j < colunas; j++) {
            int maximo = 1; // Mínimo de 1 espaço
            for (int i = 0; i < linhas; i++) {
                if (matriz[i][j] != null) {
                    maximo = Math.max(maximo, matriz[i][j].length());
                }
            }
            largurasColunas[j] = maximo;
        }

        // 2. Calcular a largura total da tabela para assegurar simetria
        int larguraTotal = 0;
        for (int larg : largurasColunas) {
            larguraTotal += larg + 2; // +2 para o padding (1 espaço de cada lado)
        }
        larguraTotal += colunas + 1; // Para as barras verticais │

        // 3. Ajustar as larguras das colunas para distribuir o espaço uniformemente se necessário
        // Este passo não é necessário para o ajuste automático básico

        // 4. Construir as linhas horizontais da tabela
        StringBuilder linhaSuper = new StringBuilder("┌");
        StringBuilder linhaMeio = new StringBuilder("├");
        StringBuilder linhaInfer = new StringBuilder("└");

        for (int j = 0; j < colunas; j++) {
            String tracejado = "─".repeat(largurasColunas[j] + 2); // +2 para o padding
            linhaSuper.append(tracejado).append(j < colunas - 1 ? "┬" : "┐");
            linhaMeio.append(tracejado).append(j < colunas - 1 ? "┼" : "┤");
            linhaInfer.append(tracejado).append(j < colunas - 1 ? "┴" : "┘");
        }

        // 5. Imprimir a tabela completa
        System.out.println(linhaSuper);

        for (int i = 0; i < linhas; i++) {
            System.out.print("│");
            for (int j = 0; j < colunas; j++) {
                String celula = matriz[i][j] != null ? matriz[i][j] : "";
                // Formata a célula com padding e alinhamento à esquerda
                System.out.print(" " + String.format("%-" + largurasColunas[j] + "s", celula) + " │");
            }
            System.out.println();

            // Imprime a linha do meio entre as linhas (exceto após a última linha)
            if (i < linhas - 1) {
                System.out.println(linhaMeio);
            }
        }

        System.out.println(linhaInfer);
    }

}
