
public class DesEncryption {

	public static void main(String[] args) {
		try {
			//plain text
			final String plainText = "PSALTIKI";
			
			//key hexadecimal format
			final String primaryKeyHexadecimal = "F46Ε986435465354";
			
			
			
			//Intro messages
			System.out.println("This programm was developed for the ATS2018 course, Project 2.");
			Thread.sleep(3000); //pause for 3000 miliseconds
			System.out.println("What it does is encrypting the 8 first letters of my last name (" + plainText + "),"
					          +"using the DES cipher.");
			Thread.sleep(4000);
			System.out.println("The given encryption key is " + primaryKeyHexadecimal + " in hexadecimal (16) format.");
			Thread.sleep(3000);
			System.out.println("Also the encryption stages, such as key permutation will be shown.");
			Thread.sleep(3000);
			System.out.println("Let's start!");
			Thread.sleep(1000);
			
			
			//variable declaration
			//counters
			int i, j, n, m;
			
			//plain text binary format
			final String plainTextBinary;
			
			//permutated binary plaintext
			String permutedPlainTextBinary = "";
			
			//key binary format
			final String primaryKeyBinary;
			
			//permuted key (56 bit)
			String permutedKeyBinary = "";
			
			//split-up permuted key
			String permutedKeyBinaryL;
			String permutedKeyBinaryR;
			
			//all 16 stages of shifts on the split-up key
			String[] permutedKeyBinaryArrayL = new String[16];
			String[] permutedKeyBinaryArrayR = new String[16];
			
			//temporary variables to make the shift
			String shiftedBitsL, otherBitsL;
			String shiftedBitsR, otherBitsR;
			
			//concatenated keys 
			String[] concatenatedSubkeys = new String[16];
			
			//encrypted text without permutation
			String ciphertextNotPermutedBinary;
			
			//final encrypted text
			String ciphertextBinary="";
			
			//split-up permutated binary plaintext
			String[] permutedPlainTextBinaryL = new String[17];
			String[] permutedPlainTextBinaryR = new String[17];
			//initializing them
			for(i=0; i<=16; i++) {
				permutedPlainTextBinaryL[i] = "";
				permutedPlainTextBinaryR[i] = "";
			}
			
			//expanding the right half of the plaintext
			String[] expandedPermutedPlainTextBinaryR = new String[16];
			//initializing it
			for(n=0; n<=15; n++) {
				expandedPermutedPlainTextBinaryR[n] = "";
			}
			
			//expanded right half XOR subkey
			String[] expandedPermutedPlainTextBinaryR_XOR_concatenatedKey = new String[16];
			//initializing it
			for(n=0; n<=15; n++) {
				expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n] = "";
			}
			
			//the XORed text through the substitution boxes
			String[] sBoxesOutput = new String[16];
			//initializing it
			for(n=0; n<=15; n++) {
				sBoxesOutput[n] = "";
			}
			
			//permutation of the S boxes' output
			String[] permutedSBoxesOutput = new String[16];
			//initializing it
			for(n=0; n<=15; n++) {
				permutedSBoxesOutput[n] = "";
			}
			
			
			
			
			
			
			
			
			
			
			
			
			//converting key to binary format, 64 bits
			primaryKeyBinary = (Integer.toBinaryString(Integer.parseInt("F46E986", 16))) + "0"
					       +(Integer.toBinaryString(Integer.parseInt("4354653", 16))) + "0"
					       +(Integer.toBinaryString(Integer.parseInt("54", 16)));
			System.out.println("Converting key in binary format \n" + primaryKeyBinary + "\nit consists of 64 bits. ");
			Thread.sleep(3000);
			System.out.println("----------------------------------------------");
			
			//creating the permuted key, first permutation, 56 bits
			for(i=57; i<=59; i++) {
				for(j=i; j>0; j-=8) {
					permutedKeyBinary += primaryKeyBinary.substring(j-1, j);
				}
			}
			for(j=60; j>=36; j-=8) {
				permutedKeyBinary += primaryKeyBinary.substring(j-1, j);
			}
			for(i=63; i>=61; i--) {
				for(j=i; j>=5; j-=8) {
					permutedKeyBinary += primaryKeyBinary.substring(j-1, j);
				}
			}
			for(j=28; j>0; j-=8) {
				permutedKeyBinary += primaryKeyBinary.substring(j-1, j);
			}
			
			System.out.println("Permuting the key into \n" + permutedKeyBinary + "\nit now consists of 56 bits.");
			Thread.sleep(3000);
			System.out.println("----------------------------------------------");
			
			
			
			
			
			
			//spliting the permuted key into 2 halves
			permutedKeyBinaryL = permutedKeyBinary.substring(0, permutedKeyBinary.length()/2);
			permutedKeyBinaryR = permutedKeyBinary.substring(permutedKeyBinary.length()/2, permutedKeyBinary.length());
			
			
			
			System.out.println("Spliting the permuted key into 2 halves\n" + permutedKeyBinaryL);
			System.out.println(permutedKeyBinaryR + "\nthat are 28 bits each.");
			Thread.sleep(3000);
			System.out.println("----------------------------------------------");
			
			
			
			
			
			//making the 16 left shifts on the new split-up keys
			//1 ~ 2 shifting 1 bit
			for(i=0; i<=1; i++) {
				//the shift on the one of the two halves
				shiftedBitsL = permutedKeyBinaryL.substring(0, 1);
				otherBitsL = permutedKeyBinaryL.substring(1, permutedKeyBinaryL.length());
				permutedKeyBinaryArrayL[i] = otherBitsL + shiftedBitsL;
				//the shift on the second of the halves
				shiftedBitsR = permutedKeyBinaryR.substring(0, 1);
				otherBitsR = permutedKeyBinaryR.substring(1, permutedKeyBinaryR.length());
				permutedKeyBinaryArrayR[i] = otherBitsR + shiftedBitsR;
			}
			//2 ~ 8 shifting 2bits
			for(i=2; i<=7; i++) {
				//the shift on the one of the two halves
				shiftedBitsL = permutedKeyBinaryL.substring(0, 2);
				otherBitsL = permutedKeyBinaryL.substring(2, permutedKeyBinaryL.length());
				permutedKeyBinaryArrayL[i] = otherBitsL + shiftedBitsL;
				//the shift on the second of the halves
				shiftedBitsR = permutedKeyBinaryR.substring(0, 2);
				otherBitsR = permutedKeyBinaryR.substring(2, permutedKeyBinaryR.length());
				permutedKeyBinaryArrayR[i] = otherBitsR + shiftedBitsR;
			}
			//9 shifting 1 bit
			//the shift on the one of the two halves
			shiftedBitsL = permutedKeyBinaryL.substring(0, 1);
			otherBitsL = permutedKeyBinaryL.substring(1, permutedKeyBinaryL.length());
			permutedKeyBinaryArrayL[8] = otherBitsL + shiftedBitsL;
			//the shift on the second of the halves
			shiftedBitsR = permutedKeyBinaryR.substring(0, 1);
			otherBitsR = permutedKeyBinaryR.substring(1, permutedKeyBinaryR.length());
			permutedKeyBinaryArrayR[8] = otherBitsR + shiftedBitsR;
			//10 ~ 15 shifting 2 bits
			for(i=9; i<=14; i++) {
				//the shift on the one of the two halves
				shiftedBitsL = permutedKeyBinaryL.substring(0, 2);
				otherBitsL = permutedKeyBinaryL.substring(2, permutedKeyBinaryL.length());
				permutedKeyBinaryArrayL[i] = otherBitsL + shiftedBitsL;
				//the shift on the second of the halves
				shiftedBitsR = permutedKeyBinaryR.substring(0, 2);
				otherBitsR = permutedKeyBinaryR.substring(2, permutedKeyBinaryR.length());
				permutedKeyBinaryArrayR[i] = otherBitsR + shiftedBitsR;
			}
			//16 shifting 1 bit
			//the shift on the one of the two halves
			shiftedBitsL = permutedKeyBinaryL.substring(0, 1);
			otherBitsL = permutedKeyBinaryL.substring(1, permutedKeyBinaryL.length());
			permutedKeyBinaryArrayL[15] = otherBitsL + shiftedBitsL;
			//the shift on the second of the halves
			shiftedBitsR = permutedKeyBinaryR.substring(0, 1);
			otherBitsR = permutedKeyBinaryR.substring(1, permutedKeyBinaryR.length());
			permutedKeyBinaryArrayR[15] = otherBitsR + shiftedBitsR;
			
			System.out.println("Making the left shifts on the split keys...");
			Thread.sleep(1000);
			System.out.println("Left shifts were successful");
			Thread.sleep(1000);
			
			
			
			System.out.println("Creating the subkeys");
			//creating the concatenated subkeys, second permutation, 48 bits per key
			for(i=0; i<=15; i++) {
				String temp;
				temp = permutedKeyBinaryArrayL[i] + permutedKeyBinaryArrayR[i];
				concatenatedSubkeys[i]  = temp.substring(13, 14);
				concatenatedSubkeys[i] += temp.substring(16, 17);
				concatenatedSubkeys[i] += temp.substring(10, 11);
				concatenatedSubkeys[i] += temp.substring(23, 24);
				concatenatedSubkeys[i] += temp.substring(0 ,  1);
				concatenatedSubkeys[i] += temp.substring(4 ,  5);
				concatenatedSubkeys[i] += temp.substring(2 ,  3);
				concatenatedSubkeys[i] += temp.substring(27, 28);
				concatenatedSubkeys[i] += temp.substring(14, 15);
				concatenatedSubkeys[i] += temp.substring( 5,  6);//10
				
				concatenatedSubkeys[i] += temp.substring(20, 21);
				concatenatedSubkeys[i] += temp.substring( 9, 10);
				concatenatedSubkeys[i] += temp.substring(22, 23);
				concatenatedSubkeys[i] += temp.substring(18, 19);
				concatenatedSubkeys[i] += temp.substring(11, 12);
				concatenatedSubkeys[i] += temp.substring( 3,  4);
				concatenatedSubkeys[i] += temp.substring(25, 26);
				concatenatedSubkeys[i] += temp.substring( 7,  8);
				concatenatedSubkeys[i] += temp.substring(15, 16);
				concatenatedSubkeys[i] += temp.substring( 6,  7);//20
				
				concatenatedSubkeys[i] += temp.substring(26, 27);
				concatenatedSubkeys[i] += temp.substring(19, 20);
				concatenatedSubkeys[i] += temp.substring(12, 13);
				concatenatedSubkeys[i] += temp.substring( 1,  2);
				concatenatedSubkeys[i] += temp.substring(40, 41);
				concatenatedSubkeys[i] += temp.substring(51, 52);
				concatenatedSubkeys[i] += temp.substring(30, 31);
				concatenatedSubkeys[i] += temp.substring(36, 37);
				concatenatedSubkeys[i] += temp.substring(46, 47);
				concatenatedSubkeys[i] += temp.substring(54, 55);//30
				
				concatenatedSubkeys[i] += temp.substring(29, 30);
				concatenatedSubkeys[i] += temp.substring(39, 40);
				concatenatedSubkeys[i] += temp.substring(50, 51);
				concatenatedSubkeys[i] += temp.substring(44, 45);
				concatenatedSubkeys[i] += temp.substring(32, 33);
				concatenatedSubkeys[i] += temp.substring(47, temp.length());
				concatenatedSubkeys[i] += temp.substring(43, 44);
				concatenatedSubkeys[i] += temp.substring(48, 49);
				concatenatedSubkeys[i] += temp.substring(38, 39);
				concatenatedSubkeys[i] += temp.substring(55, 56);//40
				
				concatenatedSubkeys[i] += temp.substring(33, 34);
				concatenatedSubkeys[i] += temp.substring(52, 53);
				concatenatedSubkeys[i] += temp.substring(45, 46);
				concatenatedSubkeys[i] += temp.substring(41, 42);
				concatenatedSubkeys[i] += temp.substring(49, 50);
				concatenatedSubkeys[i] += temp.substring(35, 36);
				concatenatedSubkeys[i] += temp.substring(28, 29);
				concatenatedSubkeys[i] += temp.substring(31, 32);//48
				//oof
				System.out.println("Subkey no. " + (i+1) + " \n" + concatenatedSubkeys[i]);
				Thread.sleep(500);
				System.out.println("----------------------------------------------");
			}
			
			
			
			
			
			
			//converting plaintext (PSALTIKI) to binary format
			plainTextBinary = "0101000001010011010000010100110001010100010010010100101101001001";
			System.out.println("Converting the plain text to binary format\n" + plainTextBinary + "\nit consists of 64 bits."); 
			Thread.sleep(3000);
			System.out.println("----------------------------------------------");
			
			
			
			//permuting the binary plaintext
			for(i=58; i<=64; i+=2) {
				for(j=i; j>0; j-=8) {
					permutedPlainTextBinary += plainTextBinary.substring(j-1, j);
				}
			}
			for(i=57; i<=63; i+=2) {
				for(j=i; j>0; j-=8) {
					permutedPlainTextBinary += plainTextBinary.substring(j-1, j);
				}
			}
			System.out.println("Permuting the binary plain text\n" + permutedPlainTextBinary + "\nit still is 64 bits");
			Thread.sleep(3000);
			System.out.println("----------------------------------------------");
			
			
			
			
			//spliting the permuted plaintext
			permutedPlainTextBinaryL[0] = permutedPlainTextBinary.substring(0, permutedPlainTextBinary.length()/2);
			permutedPlainTextBinaryR[0] = permutedPlainTextBinary.substring(permutedPlainTextBinary.length()/2, permutedPlainTextBinary.length());
			
			
			System.out.println("Spliting the permuted binary text into two halves, 32 bits each.");
			Thread.sleep(3000);
			System.out.println("----------------------------------------------");
			
			
			
			
			
			//here comes the hard part
			
			
			
			
			
			System.out.println("Starting the cipher process...");
			Thread.sleep(3000);
			System.out.println("Each time we get");
			System.out.println("Ln = Rn-1 \n"
					         + "Rn = Ln-1 XOR f(Rn-1, Kn)");
			System.out.println("Where n=16,  L, R are the two halves of the plaintext 32bits each,\n"
					          +"and f is a function that expands Rn, filters it through S-boxes and then permutes the output "
					          +"of the S-boxes.");
			Thread.sleep(3000);
			System.out.println("----------------------------------------------");
			
			
			
			
			
			//strating the cipher process
			for(n=1; n<=16; n++) {
				System.out.println("\n\n\n----------------------------------------------");
				System.out.println("ITERATION NO. " + n + "/16.");
				System.out.println("----------------------------------------------");
				
				permutedPlainTextBinaryL[n] = permutedPlainTextBinaryR[n-1];
				//expanding permutedPlainTextBinaryTextR from 32 bits to 48
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring(31,permutedPlainTextBinaryR[n-1].length());
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring( 0, 1);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring( 1, 2);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring( 2, 3);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring( 3, 4);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring( 4, 5);
				for(i=4; i<=24; i+=4) {
					for(j=i; j<=i+5; j++) {
						expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring(j-1, j);
					}
				}
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring(27,28);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring(28,29);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring(29,30);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring(30,31);
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring(31,permutedPlainTextBinaryR[n-1].length());
				expandedPermutedPlainTextBinaryR[n-1] += permutedPlainTextBinaryR[n-1].substring( 0, 1);
				
				
				System.out.println("Expanding the right half of the permuted plain text from \n" + permutedPlainTextBinaryR[n-1] + "\n32 bits, to ");
				System.out.println(expandedPermutedPlainTextBinaryR[n-1] + "\n48 bits.");
				Thread.sleep(1000);
				System.out.println("----------------------------------------------");
				
				
				//expandedPermutedPlainTextBinaryTextR XOR concatenatedSubkeys
				for(m=0; m<48; m++) {
					if(m!=47) {
						if(expandedPermutedPlainTextBinaryR[n-1].substring(m, m+1).equals(concatenatedSubkeys[n-1].substring(m, m+1))) {
							expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1] += "0";
						}else {
							expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1] += "1";
						}
					}else {
						if(expandedPermutedPlainTextBinaryR[n-1].substring(m, expandedPermutedPlainTextBinaryR[n-1].length()).equals(concatenatedSubkeys[n-1].substring(m, concatenatedSubkeys[n-1].length()))) {
							expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1] += "0";
						}else {
							expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1] += "1";
						}
					}
				}
				
				System.out.println("XORing the expanded right half with the subkey we get\n" + 
				                   expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1]);
				Thread.sleep(1000);
				System.out.println("----------------------------------------------");
				
				
				//"filtering" the 48 XORed bits through their S boxes
				//this is gonna hurt a lot
				for(m=0; m<48; m+=6) {
					if(m==0) {
						//block 1 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1010";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0110";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1100";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0101";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1001";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0000";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0111";               
							}
						}
						//block 1 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0110";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1100";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1011";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1001";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0101";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0011";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1000";               
							}
						}
						//block 1 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1100";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1001";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0011";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1010";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0101";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0000";               
							}
						}
						//block 1 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1011";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0011";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1010";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0000";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0110";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1101";               
							}
						}
						
					}else if(m==6) {
						//block 2 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0111";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0010";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1101";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1100";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0000";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0101";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1010";               
							}
						}
						//block 2 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0000";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0001";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1010";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1001";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1011";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0101";               
							}
						}
						//block 2 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1000";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1100";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0110";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1001";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0011";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0010";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1111";               
							}
						}
						//block 2 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0110";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0111";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1100";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0000";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0101";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1110";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1001";               
							}
						}
						
					}else if(m==12) {
						//block 3 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1101";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1100";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1011";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0100";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0010";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1000";               
							}
						}
						//block 3 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1000";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0101";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1100";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1011";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1111";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0001";               
							}
						}
						//block 3 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0001";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0010";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1100";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0101";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1010";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1110";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0111";               
							}
						}
						//block 3 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1111";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1110";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0011";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1011";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0101";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0010";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1100";               
							}
						}
						
					}else if(m==18) {
						//block 4 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0010";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1000";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0101";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1011";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1100";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0100";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1111";               
							}
						}
						//block 4 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0111";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0010";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1100";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0001";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1010";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1110";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1001";               
							}
						}
						//block 4 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0001";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0011";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0101";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0010";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0100";               
							}
						}
						//block 4 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0100";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0101";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1011";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1100";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0111";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0010";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1110";               
							}
						}
						
					}else if(m==24) {
						//block 5 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0101";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0011";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1111";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1101";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0000";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1110";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1001";               
							}
						}
						//block 5 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0000";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1111";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1010";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0011";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1001";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0110";               
							}
						}
						//block 5 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1001";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1100";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0101";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0011";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0000";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1110";               
							}
						}
						//block 5 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1111";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0000";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1001";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1010";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0100";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0101";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0011";               
							}
						}
					}else if(m==30) {
						//block 6 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1101";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0011";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0100";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1110";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0111";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0101";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1011";               
							}
						}
						//block 6 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0001";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1101";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0000";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1011";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0011";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1000";               
							}
						}
						//block 6 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0000";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0100";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1010";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0001";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1101";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1011";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0110";               
							}
						}
						//block 6 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0101";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1110";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0001";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0000";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1101";               
							}
						}
						
					}else if(m==36) {
						//block 7 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1100";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1001";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0101";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1010";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0110";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0001";               
							}
						}
						//block 7 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0011";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0101";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1100";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0010";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1111";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0110";               
							}
						}
						//block 7 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1111";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0110";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1000";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0000";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0101";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1001";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0010";               
							}
						}
						//block 7 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0101";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0000";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1111";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1110";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0010";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0011";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1100";               
							}
						}
						
					}else if(m==42) {
						//block 8 row 0
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0110";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1011";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1001";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0011";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1110";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0101";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0000";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1100";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0111";               
							}
						}
						//block 8 row 1
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("0") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "0011";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0101";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "0110";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1011";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0000";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "1110";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "1001";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "0010";               
							}
						}
						//block 8 row 2
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("0")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "1001";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1100";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "0000";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "0110";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1010";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "1101";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "1111";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0011";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0101";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1000";               
							}
						}
						//block 8 row 3
						if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m, m+1).equals("1") && expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+5, m+6).equals("1")) {
							                                                                                       //col 0
							if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0000")) {
								sBoxesOutput[n-1] += "0010";                                                             //col 1
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0001")) {
								sBoxesOutput[n-1] += "0001";                                                             //col 2
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0010")) {
								sBoxesOutput[n-1] += "1110";                                                             //col 3
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0011")) {
								sBoxesOutput[n-1] += "0111";                                                             //col 4
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0100")) {
								sBoxesOutput[n-1] += "0100";                                                             //col 5
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0101")) {
								sBoxesOutput[n-1] += "1010";                                                             //col 6
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0110")) {
								sBoxesOutput[n-1] += "1000";                                                             //col 7 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("0111")) {
								sBoxesOutput[n-1] += "1101";                                                             //col 8
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1000")) {
								sBoxesOutput[n-1] += "1111";                                                             //col 9
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1001")) {
								sBoxesOutput[n-1] += "1100";                                                             //col10 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1010")) {
								sBoxesOutput[n-1] += "1001";                                                             //col11 
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1011")) {
								sBoxesOutput[n-1] += "0000";                                                             //col12
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1100")) {
								sBoxesOutput[n-1] += "0011";                                                             //col13
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1101")) {
								sBoxesOutput[n-1] += "0101";                                                             //col14
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1110")) {
								sBoxesOutput[n-1] += "0110";                                                             //col15
							}else if(expandedPermutedPlainTextBinaryR_XOR_concatenatedKey[n-1].substring(m+1, m+5).equals("1111")) {
								sBoxesOutput[n-1] += "1011";               
							}
						}
						
					}
					
				}//OOF
				
				System.out.println("Filtering the XORed result (48 bits) through its S-boxes \n" + sBoxesOutput[n-1] +
						           "\nit is 32 now.");
				Thread.sleep(1000);
				System.out.println("----------------------------------------------");
				
				
				
				
				
				
				//permuting the S boxes' output
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(15, 16);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 6,  7);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(19, 20);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(20, 21);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(28, 29);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(11, 12);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(27, 28);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(16, 17);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 0,  1);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(14, 15);//10
				
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(22, 23);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(25, 26);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 4,  5);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(17, 18);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(30, 31);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 9, 10);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 1,  2);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 7,  8);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(23, 24);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(13, 14);//20
				
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(31, sBoxesOutput[n-1].length());
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(26, 27);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 2,  3);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 8,  9);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(18, 19);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(12, 13);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(29, 30);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 5,  6);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(21, 22);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(10, 11);//30
				
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring( 3,  4);
				permutedSBoxesOutput[n-1] += sBoxesOutput[n-1].substring(24, 25);//32
				
				
				System.out.println("Permuting the S-boxes' output into\n" + permutedSBoxesOutput[n-1]);
				Thread.sleep(1000);
				System.out.println("----------------------------------------------");
				
				
				//permutedPlainTextBinaryTextL XOR permutedSBoxesOutput
				for(m=0; m<32; m++) {
					if(m!=31) {
						if(permutedPlainTextBinaryL[n-1].substring(m, m+1).equals(permutedSBoxesOutput[n-1].substring(m, m+1))) {
							permutedPlainTextBinaryR[n] += "0";
						}else {
							permutedPlainTextBinaryR[n] += "1";
						}
					}else {
						if(permutedPlainTextBinaryL[n-1].substring(m, m+1).equals(permutedSBoxesOutput[n-1].substring(m, m+1))) {
							permutedPlainTextBinaryR[n] += "0";
						}else {
							permutedPlainTextBinaryR[n] += "1";
						}
					}
				}
				System.out.println("XORing the the L" + (n-1) + " with the permuted S-boxes output\n" + permutedPlainTextBinaryR[n]);
				Thread.sleep(500);
				System.out.println("----------------------------------------------");
				if(n==16) {
					System.out.println("End of iterations.\n\n");
				}
					
			}
			
			System.out.println("\n\n");
			
			
			//one eternity later  
			
			
			ciphertextNotPermutedBinary = permutedPlainTextBinaryR[16] + permutedPlainTextBinaryL[16];
			
			System.out.println("----------------------------------------------");
			System.out.println("The not permuted cipher text\n" + ciphertextNotPermutedBinary);
			Thread.sleep(1000);
			System.out.println("----------------------------------------------");
			
			
			//performing the final permutation and getting the permuted encrypted text
			for(i=40; i>=33; i--) {
				ciphertextBinary += ciphertextNotPermutedBinary.substring( i-1,   i);
				ciphertextBinary += ciphertextNotPermutedBinary.substring(i-33,i-32);
				ciphertextBinary += ciphertextNotPermutedBinary.substring( i+7, i+8);
				ciphertextBinary += ciphertextNotPermutedBinary.substring(i-24,i-23);
				ciphertextBinary += ciphertextNotPermutedBinary.substring(i+15,i+16);
				ciphertextBinary += ciphertextNotPermutedBinary.substring(i-16,i-15);
				ciphertextBinary += ciphertextNotPermutedBinary.substring(i+23,i+24);
				ciphertextBinary += ciphertextNotPermutedBinary.substring( i-8, i-7);
			}
			
			System.out.println("Now performing the final permutation...\ncipher text is");
			System.out.println(ciphertextBinary +"\nin binary format.");
			Thread.sleep(1000);
			System.out.println("\n\n\nThe end.");
			
			
			
			
			
		}catch(InterruptedException e) {
			System.out.println("System error; " + e);
		}

	}

}
