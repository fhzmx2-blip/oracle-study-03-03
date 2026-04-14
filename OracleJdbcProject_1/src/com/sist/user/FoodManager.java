package com.sist.user;

import java.util.*;

import com.sist.dao.*;
import com.sist.vo.*;

import java.io.*;

public class FoodManager {
    public static void main(String[] args) {
        List<FoodVO> fList = new ArrayList<FoodVO>();
        // 1. 파일 읽기 (BufferedReader 권장)
        try (BufferedReader br = new BufferedReader(new FileReader("c:/javaDev/food.txt"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) continue; // 빈 줄 스킵

                // split 이용 (limit -1은 마지막 빈 데이터까지 포함한다는 뜻)
                String[] data = line.split("\\|", -1); 

                FoodVO f = new FoodVO();
                f.setNo(Integer.parseInt(data[0].trim()));
                f.setName(data[1]);
                f.setType(data[2]);
                f.setPhone(data[3]);
                f.setAddress(data[4]);
                f.setScore(Double.parseDouble(data[5].trim()));
                f.setParking(data[6]);
                f.setPoster("http://menupan.com" + data[7]);
                f.setTime(data[8]);
                f.setContent(data[9]);
                f.setTheme(data[10]);
                f.setPrice(data[11]);

                fList.add(f);
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // 에러 발생 시 로그 확인 필수
        }

        // 2. DB 저장
        FoodDAO dao = FoodDAO.newInstance();
        int k = 1;
        for (FoodVO vo : fList) {
            dao.foodInsert(vo);
            System.out.println(k + " ROW 저장 완료: " + vo.getName());
            k++;
        }
    }
}