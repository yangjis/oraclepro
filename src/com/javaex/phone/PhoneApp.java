package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		PhoneDao pDao = new PhoneDao();
		List<PersonVo> pList = new ArrayList<PersonVo>();
		
		Scanner scan = new Scanner(System.in);
		int num;
		int count;
		
		System.out.println("**************************************");
		System.out.println("*            전화번호 관리 플로그램               *");
		System.out.println("**************************************");
		
		boolean button = true;
		while(button) {
			System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			System.out.println("-------------------------------------");
			System.out.print(">메뉴번호: ");
			num = scan.nextInt();
			
			switch(num) {
			case 1 :
				System.out.println("<1.리스트>");
				pList = pDao.select("");
				for(PersonVo vo1: pList) {
					System.out.println(vo1.getPerson_id() + "\t" + vo1.getName() + "\t" + vo1.getHp() + "\t" + vo1.getCompany());
				}
				System.out.println();
				break;
			case 2:
				System.out.println("<2.등록>");
				
				System.out.print("이름 >");
				String pName = scan.next();
				
				System.out.print("휴대전화 >");
				String pHp = scan.next();
				
				System.out.print("회사번호 >");
				String pCompany = scan.next();
				
				PersonVo vo = new PersonVo(pName, pHp, pCompany);
				
				count = pDao.insert(vo);
				System.out.println("[" + count + "건이 등록 되었습니다.]");
				break;
			case 3:
				System.out.println("<3.수정>");
				
				System.out.print("번호 > ");
				int updateNum = scan.nextInt();
				
				System.out.print("이름 > ");
				String updateName = scan.next();
				
				System.out.print("휴대전화 > ");
				String updateHp = scan.next();
				
				System.out.print("회사번호 > ");
				String updateCompany = scan.next();
				
				PersonVo vo1 = new PersonVo(updateNum, updateName, updateHp, updateCompany);
				count = pDao.update(vo1);
				System.out.println("[" + count + "건이 수정 되었습니다.]");
				break;
			case 4:
				System.out.println("<4.삭제>");
				System.out.print(">번호 :");
				int deleteNum = scan.nextInt();
				
				count = pDao.delete(deleteNum);
				System.out.println("[" + count + "건이 삭제 되었습니다.]");
				break;
			case 5:
				System.out.println("<5.검색>");
				System.out.print("검색어 > ");
				String keyword = scan.next();
				pList = pDao.select(keyword);
				
				for(PersonVo vo5: pList) {
					System.out.println(vo5.getPerson_id() + "\t" + vo5.getName() + "\t" + vo5.getHp() + "\t" + vo5.getCompany());
				}
				break;
			case 6:
				button = false;
				break;
			default:
				System.out.println("[다시 입력해주세요.]");
				break;
			}
		}
		System.out.println("**************************************");
		System.out.println("*             감사합니다.               *");
		System.out.println("**************************************");
		
		scan.close();

	}
}
