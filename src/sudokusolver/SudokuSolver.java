//null will be represented by 0

package sudokusolver;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Matthew Wohlbach
 */
public class SudokuSolver {
    
    static int changes=0;
    static int testchanges=0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IndexOutOfBoundsException {
        File puzzle = new File("puzzle9.txt");
        Scanner readpuzzle = new Scanner(puzzle);
        File solution = new File("solution9.txt");
        Scanner readsolution = new Scanner(solution);
        int[][] sudokupuzzle = new int[9][9];
        int[][] sudokuattempt = new int[9][9];
        int[][] sudokusolution = new int[9][9];
       
        
        for(int j=0; j<9; j++){
            String row = readsolution.nextLine();
            String[] rowspl=row    .split(",");
        
        
                for(int i=0; i<rowspl.length; i++){
                        if(rowspl[i].isEmpty()){    
                               // System.out.println(0);
                        }
                        
                        else {
                             int num = Integer.parseInt(rowspl[i]);
                             //System.out.println(num);
                             sudokusolution[j][i] = num;
                            
                        }
        }
        }
       
        
        
        for(int j=0; j<9; j++){
            String row = readpuzzle.nextLine();
            String[] rowspl=row    .split(",");
        
        
                for(int i=0; i<rowspl.length; i++){
                        if(rowspl[i].isEmpty()){    
                               // System.out.println(0);
                        }
                        
                        else {
                             int num = Integer.parseInt(rowspl[i]);
                             //System.out.println(num);
                             sudokupuzzle[j][i] = num;
                             sudokuattempt[j][i] = num;
                        }
        }
        }
       
        
        
        
        
        while(changes==0){
        changes=1;    
        sudokuattempt=threexthreeelimination(sudokuattempt);
        sudokuattempt=ninexoneeliminationhorizontal(sudokuattempt);
        sudokuattempt=ninexoneeliminationvertical(sudokuattempt);
        sudokuattempt=threexthreeprocessofelimination(sudokuattempt);
        System.out.println("Changes: " + changes);
        }
        
        
   
        printPuzzle(sudokupuzzle,"Sudoku Start");
        printPuzzle(sudokuattempt,"Sudoku Attempt");
        printPuzzle(sudokusolution,"Sudoku Solution");
        
    }
    
    public static void printPuzzle(int[][] A,String B){
        System.out.print("             " + B);
        int thirdcounter=0;
        int counter=0;
         for(int i=0;i<A.length;i++){
             System.out.println(); 
             if(counter%3!=0){
             System.out.println("||-------------------------------------||");}
             else{
                 System.out.println("||=====================================||");
                 
             }
             System.out.print("");
    
             counter++;
                   
            for(int j=0;j<A[0].length;j++){
                if(thirdcounter%3!=0){
                    System.out.print("|");
                }
                else{
                    System.out.print("||");
                }
                if(A[i][j] ==0){
                    System.out.print("   ");
                }
                else{
                System.out.print(" " + A[i][j] + " ");
                }
                if(j==A[0].length-1){
                    System.out.print("||");
                }
                thirdcounter++;
            }
         }
         System.out.println();
         System.out.println("||=====================================||");
         System.out.println();
    }
    
    public static void print9x1(int[] A){
        for(int i=0;i<9;i++){
            System.out.print(" " + A[i]);
        }
        System.out.println();
    }
    
    public static int[][] ninexoneeliminationvertical(int[][] A){
        
        for(int i=0; i<9; i++){
            //int start1=i;
            
            
            //create arrays
            //int[] includedvalues = new int[9];
            int[] nxo = new int[9];
            for(int j=0; j<9;j++){
                nxo[j]= A[j][i];
            }
            //print9x1(nxo);
            // end create arrays
            
            
            
            for(int w=1; w<10; w++){
             
                
               //checks same box for number and sets empty count
               boolean alrdy = true;
               int emptycount=0;
                
                for(int k=0;k<9;k++){
                  //  System.out.println("w : " + w   + "           nxo[k] = " + nxo[k]);
                    if(w==nxo[k]){
                        alrdy=false;
                    }
                    if(nxo[k]==0){
                        emptycount++;
                    }
                }
                //System.out.println("empty counter : " + emptycount   + "           alrdy = " + alrdy);
                //end checks same box for number and sets empty count
                
                
                if(alrdy==true){
                 
                    
                  int hitcount=0;
                  int[] placeholders=  new int[9];  
                    
                  for(int xx=0; xx<9; xx++){
                      
                      if(nxo[xx]==0){
                          
                          int hitcountwatch=0;
                          for(int yy=0; yy<9; yy++){
                              int start1=0;
                              int start2=0;
                              if(i==0 || i==1 || i==2)
                                  start1=0;
                              if(i==3 || i==4 || i==5)
                                  start1=3;
                              if(i==6 || i==7 || i==8)
                                  start1=6;
                              if(xx==0 || xx==1 || xx==2)
                                  start2=0;
                              if(xx==3 || xx==4 || xx==5)
                                  start2=3;
                              if(xx==6 || xx==7 || xx==8)
                                  start2=6;
                              //System.out.println("start1 - " + start1 + "           start2 - " + start2);
                              int collide=0;
                              for(int e=0;e<3;e++){
                                  for(int f=0;f<3;f++){
                                      if(A[start2+f][start1+e]==w){
                                          collide=1;
                                          //System.out.println("[start2+f] - " + (start2+f) + "           [start1+e] - " + (start1+e));
                                      }
                                  }
                              }
                            
                               if(xx<9){  
                              //System.out.println("xx - " + xx + "        yy - " + yy +       "  A[xx][yy] - " + A[xx][yy] + "         w =" + w + "           collider - " + collide);
                              
                              if(A[xx][yy]==w  
                                      || (collide==1 && nxo[xx]==0)
                                      ){
                               hitcount++;
                               hitcountwatch++;
                               
                           }
                              } 
                          
                           if(hitcountwatch==1){
                               yy=9;
                           }
                              
                          } 
                              
                          
                          
                          if(hitcountwatch==0){
                              placeholders[xx]=1;
                          } 
                          
                      }//if the space is a 0
                      
                
                      
                  } //for(xx) ends
            
                  if((emptycount-1)==hitcount){              
               for(int s=0; s<9; s++){
                   
                       if(placeholders[s]==1){
                           
                        // System.out.println("I WILL NOW ENTER A VALUE AT  ----------       x: " + s + "          y:" + i + "    w: " + w);
                        ///////   System.out.println();
                           A[s][i]=w;
                           emptycount--;
                           changes=0;
                           testchanges=0;
                           //printPuzzle(A,"test");
                       
                   } 
               }
           }
                  
                    
                }//alrdy is true
        
            }//for(w) 1-10 ends here
            
       
        } //for(i) 0-9 ends here
        
        
        
        
        
        
        return A;
    }
    
    
    
    public static int[][] ninexoneeliminationhorizontal(int[][] A){
        
        for(int i=0; i<9; i++){
            //int start1=i;
            
            
            //create arrays
            //int[] includedvalues = new int[9];
            int[] nxo = new int[9];
            for(int j=0; j<9;j++){
                nxo[j]= A[i][j];
            }
            //print9x1(nxo);
            // end create arrays
            
            
            
            for(int w=1; w<10; w++){
             
                
               //checks same box for number and sets empty count
               boolean alrdy = true;
               int emptycount=0;
                
                for(int k=0;k<9;k++){
                    //System.out.println("w : " + w   + "           nxo[k] = " + nxo[k]);
                    if(w==nxo[k]){
                        alrdy=false;
                    }
                    if(nxo[k]==0){
                        emptycount++;
                    }
                }
                //System.out.println("empty counter : " + emptycount   + "           alrdy = " + alrdy);
                //end checks same box for number and sets empty count
                
                
                if(alrdy==true){
                 
                    
                  int hitcount=0;
                  int[] placeholders=  new int[9];  
                    
                  for(int xx=0; xx<9; xx++){
                      
                      if(nxo[xx]==0){
                          
                          int hitcountwatch=0;
                          for(int yy=0; yy<9; yy++){
                              int start1=0;
                              int start2=0;
                              if(i==0 || i==1 || i==2)
                                  start1=0;
                              if(i==3 || i==4 || i==5)
                                  start1=3;
                              if(i==6 || i==7 || i==8)
                                  start1=6;
                              if(xx==0 || xx==1 || xx==2)
                                  start2=0;
                              if(xx==3 || xx==4 || xx==5)
                                  start2=3;
                              if(xx==6 || xx==7 || xx==8)
                                  start2=6;
                              
                              int collide=0;
                              for(int e=0;e<3;e++){
                                  for(int f=0;f<3;f++){
                                      if(A[start1+f][start2+e]==w){
                                          collide=1;
                                         // System.out.println("[start1+f] - " + (start1+f) + "           [start2+e] - " + (start2+e));
                                      }
                                  }
                              }
                            
                               if(xx<9){  
                              //System.out.println("yy - " + yy + "        xx - " + xx +       "  A[xx][yy] - " + A[yy][xx] + "         w =" + w + "           collider - " + collide);
                              
                              if(A[yy][xx]==w  
                                      || (collide==1 && nxo[xx]==0)
                                      ){
                               hitcount++;
                               hitcountwatch++;
                               
                           }
                              } 
                          
                           if(hitcountwatch==1){
                               yy=9;
                           }
                              
                          } 
                              
                          
                          
                          if(hitcountwatch==0){
                              placeholders[xx]=1;
                          } 
                          
                      }//if the space is a 0
                      
                
                      
                  } //for(xx) ends
            
                  if((emptycount-1)==hitcount){              
               for(int s=0; s<9; s++){
                   
                       if(placeholders[s]==1){
                           
                        ///////   System.out.println("I WILL NOW ENTER A VALUE AT  ----------       x: " + s+start1 + "          y:" + r+start2 + "    w: " + w);
                        ///////   System.out.println();
                           A[i][s]=w;
                           emptycount--;
                           changes=0;
                           testchanges=0;
                           //printPuzzle(A,"test");
                       
                   } 
               }
           }
                  
                    
                }//alrdy is true
        
            }//for(w) 1-10 ends here
            
       
        } //for(i) 0-9 ends here
        
        
        
        
        
        
        return A;
    }
    
    public static int[][] threexthreeprocessofelimination(int[][] A){
        
        for(int i=0; i<9; i++){
            
            
           //make reference values
           int start1=i*3;
           int start2=0;
           
           if(start1>6){
               start1=(9-start1)*(-1);
               start2=3;
               if(start1>6){
                   start1=(9-start1)*(-1);
                   start2=6;
               }
           }
           //end make reference values
           
           
           //make 3x3 array
           int [][] txt = new int[3][3];
           for(int a = 0; a<3; a++){
               for(int b = 0; b<3; b++){
                   txt[a][b] = A[start1+a][start2+b];
               }
           }
           //printPuzzle(A, "Keepo");
           //printPuzzle(txt, "Kappa");
           //end make 3x3 array
           
           
           
               
               
               
            //checks same box for number and sets empty count
           int emptycount=0;
           
           
               
               
           for(int x = 0; x<3 ; x++){
               for(int y=0; y<3; y++){
                   //if((w)==txt[y][x]){
                       ///////printPuzzle(txt, "Kappa");
                       ///////System.out.println("w= " + (w) + "        x :" + x + "    y :  " + y);
                       //alrdy=false;                      
                   //}
                   if(txt[y][x]==0){
                       emptycount++;
                   }
               }
           }
           
           //ends check for same box and sets empty count
           
           
           //System.out.println("emptycount : " + emptycount);
            //passed the in same box check
           //if(alrdy==true){
               //System.out.println("w= " + (w+1) );
           //int emptycount=0;
//           for(int aa = 0; aa<3; aa++){
//               for(int bb = 0; bb<3; bb++){                 
//                   if(txt[aa][bb]==0){
//                       emptycount++;
//                   }
//                   
//               }
//           }
           
          //System.out.println("start1 :" + start1 + "     start2: " + start2 );
           
           
         //for(int xx=1; xx<10; xx++){
             
             
           for(int aa = 0; aa<3; aa++){
               for(int bb = 0; bb<3; bb++){
                   //System.out.println("aa= " + aa + "       bb= " + bb + "         i= " + i);
                   if(txt[aa][bb]==0){
                       int [][] placeholders=  new int[3][3];
                       int hitcount=0;
                       
                       for(int m=1; m<10;m++){
                          
                          int hitcountwatch=0;
                          int collide=0;
                              for(int e=0;e<3;e++){
                                  for(int f=0;f<3;f++){
                                      if(A[start1+f][start2+e]==m){
                                          collide=1;
                                          //System.out.println("[start1+f] - " + (start1+f) + "           [start2+e] - " + (start2+e) + "           m= " + m);
                                      }
                                  }
                              } 
                           
                           
                       if(collide==0){    
                       for(int t=0;t<9;t++){
                           int loc1= aa+start1;
                           int loc2= bb+start2;
                           
                           
                           
                           
                            
                           
                           
                           //System.out.println("m: " + m + "       A[loc1][t]: " + A[loc1][t] +  "       A[t][loc2]: " + A[t][loc2] +  "          loc1 :" + loc1 +   "         loc 2:  " + loc2 + "                    t: " + t      + "        start2: " + start2 + "        start1: " + start1);
                           if(A[loc1][t]==m){
                               hitcount++;
                               hitcountwatch++;
                               
                           }
                           
                           if(A[t][loc2]==m){
                               if(A[loc1][t]==m){
                                   
                           }
                               else{
                               hitcount++;
                               hitcountwatch++;
                               }
                           }
                           if(hitcountwatch==1){
                               t=9;
                           }
                           
                           }//for(t) 1-10 ends
                       
                       if(hitcountwatch==0){
                              placeholders[aa][bb]=m;
                          } 
                       
                       
                       }//collide ends
                           
                           
//                           if(hitcountwatch==1){
//                               t=9;
//                           }
                          //System.out.println("hitcount: " + hitcount + "                 empty count: " + emptycount + "    m " + m   );
                          //System.out.println();
                           
                       }//for(m) 0-9 ends
                       
                       
                       if((emptycount-1)==hitcount){              
               for(int s=0; s<3; s++){
                   for(int r=0; r<3; r++){
                       if(placeholders[s][r]!=0){
                           
                          // System.out.println("I WILL NOW ENTER A VALUE AT  ----------       x: " + s+start1 + "          y:" + r+start2 + "    placeholders[s][r]: " + placeholders[s][r]);
                           
                           A[s+start1][r+start2]=placeholders[s][r];
                           emptycount--;
                           //System.out.println("emptycount is now " + emptycount);
                           changes=0;
                           testchanges=0;
                           //printPuzzle(A,"test");
                       }
                   } 
               }
           }
                       
                          
                       }//iftxt[aa][bb]==0 ends
                   
                   }
                   
               }
           //System.out.println("@@@@@@@hitcount: " + hitcount + "                 empty count: " + emptycount);
           
           
          // } // end for(xx) loop 1-9
            
         
          
           
         //  }    // end alrdy check
           
         ///// System.out.println("done with w : " + w);
        //} //for(w) loop ends here 1-9
        } //for(1) loop ends here 1-9
        
       // System.out.println("Changes : " + changes);
        
        return A;
    }
    
    public static int[][] threexthreeelimination(int[][] A){
        
        for(int i=0; i<9; i++){
            
            
           //make reference values
           int start1=i*3;
           int start2=0;
           
           if(start1>6){
               start1=(9-start1)*(-1);
               start2=3;
               if(start1>6){
                   start1=(9-start1)*(-1);
                   start2=6;
               }
           }
           //end make reference values
           
           
           //make 3x3 array
           int [][] txt = new int[3][3];
           for(int a = 0; a<3; a++){
               for(int b = 0; b<3; b++){
                   txt[a][b] = A[start1+a][start2+b];
               }
           }
           //printPuzzle(txt, "Kappa");
           //end make 3x3 array
           
           
           
           for(int w=1; w<10; w++){
               
               
               
            //checks same box for number and sets empty count
               boolean alrdy = true;
               int emptycount=0;
               
           for(int x = 0; x<3 ; x++){
               for(int y=0; y<3; y++){
                   if((w)==txt[y][x]){
                       ///////printPuzzle(txt, "Kappa");
                       ///////System.out.println("w= " + (w) + "        x :" + x + "    y :  " + y);
                       alrdy=false;                      
                   }
                   if(txt[y][x]==0){
                       emptycount++;
                   }
               }
           }
           //ends check for same box and sets empty count
           
           
           //System.out.println("emptycount : " + emptycount);
            //passed the in same box check
           if(alrdy==true){
               //System.out.println("w= " + (w+1) );
           //int emptycount=0;
//           for(int aa = 0; aa<3; aa++){
//               for(int bb = 0; bb<3; bb++){                 
//                   if(txt[aa][bb]==0){
//                       emptycount++;
//                   }
//                   
//               }
//           }
           
          //System.out.println("start1 :" + start1 + "     start2: " + start2 );
           
           
         //for(int xx=1; xx<10; xx++){
             int hitcount=0;
             int [][] placeholders=  new int[3][3];
           for(int aa = 0; aa<3; aa++){
               for(int bb = 0; bb<3; bb++){                 
                   if(txt[aa][bb]==0){
                       int hitcountwatch=0;
                       for(int t=0;t<9;t++){
                           int loc1= aa+start1;
                           int loc2= bb+start2;
                          ///////// System.out.println("w: " + w + "       A[loc1][t]: " + A[loc1][t] +  "       A[t][loc2]: " + A[t][loc2] +  "          loc1 :" + loc1 +   "         loc 2:  " + loc2 + "                    t: " + t      + "        start2: " + start2 + "        start1: " + start1);
                           if(A[loc1][t]==w && (start2>t || t>start2+2)){
                               hitcount++;
                               hitcountwatch++;
                               
                           }
                           
                           if(A[t][loc2]==w && (start1>t || t>start1+2)){
                               if(A[loc1][t]==w && (start2>t || t>start2+2)){
                                   
                           }
                               else{
                               hitcount++;
                               hitcountwatch++;
                               }
                           }
                           if(hitcountwatch==1){
                               t=9;
                           }
                          //////// System.out.println("hitcount: " + hitcount + "                 empty count: " + emptycount + "        loc "    + loc1 + "     loc " + loc2 + "    w " + w   );
                          //////// System.out.println();
                           
                       }
                          if(hitcountwatch==0){
                              placeholders[aa][bb]=1;
                          } 
                       }
                   
                   }
                   
               }
           //System.out.println("@@@@@@@hitcount: " + hitcount + "                 empty count: " + emptycount);
           if((emptycount-1)==hitcount){              
               for(int s=0; s<3; s++){
                   for(int r=0; r<3; r++){
                       if(placeholders[s][r]==1){
                           
                        ///////   System.out.println("I WILL NOW ENTER A VALUE AT  ----------       x: " + s+start1 + "          y:" + r+start2 + "    w: " + w);
                        ///////   System.out.println();
                           A[s+start1][r+start2]=w;
                           emptycount--;
                           changes=0;
                           testchanges=0;
                           //printPuzzle(A,"test");
                       }
                   } 
               }
           }
           
          // } // end for(xx) loop 1-9
            
         
          
           
           }    // end alrdy check
           
         ///// System.out.println("done with w : " + w);
        } //for(w) loop ends here 1-9
        } //for(1) loop ends here 1-9
        
        //System.out.println("Changes : " + changes);
        
        return A;
    }
    
}
