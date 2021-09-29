import java.util.*;

class regresi{
    static void gauss(double mat[][], double ans[], int row, int col){
        for(int j = 0; j < col; j++) {
            int now = 0;   
            while (j<row && j+now<col && mat[j][j+now] != 1) {
                if (mat[j][j+now] != 0 && mat[j][j+now] != 1) {
                    double f = mat[j][j+now];
                    for (int k=0; k<= col; k++) {
                        mat[j][k] /= f;
                    }
                    break;
                }
                else if (mat[j][j] == 0) {
                    now++;
                }
            }
            for(int i = j+1; i < row; i++) {
                if (mat[i][j+now] != 0.0) {
                    double f = mat[i][j+now];
                    for (int k = 0; k <= col; k++) {
                        mat[i][k] = mat[i][k] - f/(mat[j][j+now])*mat[j][k];
                    }
                }
            }
        }
        //print(mat,row,col);

        boolean solvable = true;
        int barisnol = 0;
        for(int i = row-1; i>=0; i--){
            int j = 0;
            while(j<=col && mat[i][j]==0){
                j++;
            }
            if(j ==  col){
                solvable = false;
            }
            else if(j == col+1){
                barisnol++;
            }
            else{
                break;
            }
        }

        if(solvable){
            if(row - barisnol == col){
                int newrow = row - barisnol;
                for (int i = newrow - 1; i >= 0; i--){
                    ans[i] = mat[i][col];
                    for (int j = i + 1; j < newrow; j++){
                        ans[i] -= mat[i][j] * ans[j];
                    }
                    ans[i] = ans[i] / mat[i][i];
                }
                
                System.out.println();
                System.out.println("Solusi sistem persamaan : ");
                for (int i = 0; i < col; i++){
                    System.out.format("%.6f", ans[i]);
                    System.out.println();
                }
            }
            else{
                System.out.println("Sistem persamaan memiliki solusi parametrik.");
                //solusi parametriknya belom buat.
            }
        }
        else{
            System.out.println("Sistem persamaan tidak memiliki solusi.");
        }
    }

    static void regresi(double mat[][], int n, int k){
        double temp[][] = new double[k+1][k+2];
        //compute augmented baris pertama
        temp[0][0] = n;
        for(int j=1; j<=k+1; j++){
            for(int l=0; l<n; l++){
                temp[0][j] += mat[l][j-1];
            }
        }
        //compute augmented kolom pertama
        for(int i=1; i<=k; i++){
            for(int l=0; l<n; l++){
                temp[i][0] += mat[l][i-1];
            }
        }
        //compute sisanya
        for(int i=1; i<=k; i++){
            for(int j=1; j<=k+1; j++){
                for(int l=0; l<n; l++){
                    temp[i][j] += mat[l][i-1]*mat[l][j-1];
                }
            }
        }
        double ans[] = new double[k+1];
        gauss(temp,ans, k+1, k+1);

        System.out.println("Model regresi : ");
        System.out.print("y = ");
        for (int i = 0; i < k+1; i++){
            System.out.format("%.6f", ans[i]);
            if(i == 0){
                System.out.print(" + ");
            }
            else if(i == k){
                System.out.print("x_" + i);
            }
            else{
                System.out.print("x_" + i +" + ");
            }
        }
    }

    public static void main(String[] args){
        double[][] mat = { {72.4, 76.3,0.9}, {41.6, 70.3, 0.91}, {34.3, 77.1, 0.96}, {35.1, 68.0, 0.89} };
        regresi(mat, 4, 2);
    }
}