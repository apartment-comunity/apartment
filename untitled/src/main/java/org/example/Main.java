package org.example;



public class Main {
    public static void main(String[] args) {

        System.out.println(1);
    }

    public static int LV2_다음_큰_숫자(int n) {
        int answer = n;
        while (true) {
            answer++;
            if (Integer.toBinaryString(n).replaceAll("0", "").length() == Integer.toBinaryString(answer).replaceAll("0", "").length()) {
                return answer;
            }
        }
    }

    public static int LV2_다음_큰_숫자2(int n) {
        int oneCount = Integer.bitCount(n);
        while (true) {
            n++;
            if (Integer.bitCount(n) == oneCount) {
                return n;
            }
        }
    }

    public static int LV2_숫자의_표현(int n) {
        int answer = 1;
        for (int i = 1; i < n / 2 + 1; i++) {
            int sum = 0;
            for (int j = i; j <= n / 2 + 1; j++) {
                sum = sum + j;
                if (sum > n) {
                    break;
                }
                if (sum == n) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }

    public static int[] LV2_카펫(int brown, int yellow) {
        int[] answer = {0, 0};

        int sum = (brown + 4) / 2;
        int prod = yellow + brown;

        for (int i = sum / 2; i > 0; i--) {
            int a = sum - i;
            if (a * i == prod) {
                answer[0] = a;
                answer[1] = i;
                break;
            }
        }
        return answer;
    }


    public static String LV2_최댓값과_최솟값(String s) {
        String[] numbers = s.split(" ");
        int min = Integer.parseInt(numbers[0]);
        int max = Integer.parseInt(numbers[0]);

        for (String a : numbers) {

            if (Integer.parseInt(a) > max) {
                max = Integer.parseInt(a);
            }
            if (Integer.parseInt(a) < min) {
                min = Integer.parseInt(a);
            }
        }
        return min + " " + max;
    }

    public static String LV2_JadenCase_문자열_만들기(String s) {
        String n = s + "a";
        String[] str = n.split(" ");
        String answer = "";
        for (String a : str) {
            if (a.equals("")) {
                answer = answer + " ";
                continue;
            }
            char firstChar = a.charAt(0);
            char upperCaseFirstChar = Character.toUpperCase(firstChar);
            String remainingString = a.substring(1).toLowerCase();
            answer = answer + " " + upperCaseFirstChar + remainingString;
        }
        return answer.substring(1, answer.length() - 1);
    }


    public static int LV2_최솟값_만들기(int[] A, int[] B) {

        int answer = 0;
//        //import 삭제용 주석
//        Arrays.sort(A);
//        Arrays.sort(B);
//        int[] C = new int[B.length];
//        int a = 0;
//        for (int i = B.length - 1; i >= 0; i--) {
//            answer += A[a] * B[i];
//            a++;
//        }

        return answer;
    }

    public static boolean LV2_올바른_괄호(String s) {
        String[] str = s.split("");
        int answer = 0;
        for (String a : str) {
            if (a.equals("(")) {
                answer++;
            } else {
                answer--;
            }

            if (answer < 0) {
                return false;
            }
        }

        if (answer == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] LV2_이진_변환_반복하기(String s) {
        int[] answer = new int[2];
        int a = 0; // 사라진 0 갯수
        int b = 0;
//        //import 삭제용
//        while (!Objects.equals(s, "1")) {
//            String modify = s.replaceAll("0", "");
//            a += s.length() - modify.length();
//
//            s = Integer.toBinaryString(modify.length());
//            b++;
//        }
        answer[0] = b;
        answer[1] = a;
        return answer;
    }


}

