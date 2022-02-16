package admin.trainerEnroll;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TrainerEnrollService {
	
		CmnTrainerDAO dao;
	
	// 중복 체크
	public void overlapProc(Parent trainerEnrollForm) {
		TextField trnIdTxt = (TextField) trainerEnrollForm.lookup("#trnIdTxt");
		String trnId = trnIdTxt.getText();
		if(trnId.isEmpty()) {
			CommonService.Msg("ID를 입력해주세요.");
		} else {
			dao = new CmnTrainerDAO();
			
			if(dao.SltTrnId(trnId) == null) {
				CommonService.Msg(trnId + " 은(는) 사용 가능한 ID입니다.");
			}else {
				CommonService.Msg(trnId + " 은(는) 이미 사용하고 있는 ID입니다.");
				trnIdTxt.setText(null);
			}
		}
	}
	
	// 강사 등록
	public void trnEnrollProc(Parent trainerEnrollForm) {
		TextField trnIdTxt = (TextField) trainerEnrollForm.lookup("#trnIdTxt");
		PasswordField trnPwTxt = (PasswordField) trainerEnrollForm.lookup("#trnPwTxt");
		PasswordField trnPwComfrimTxt = (PasswordField) trainerEnrollForm.lookup("#trnPwComfrimTxt");
		TextField trnNameTxt = (TextField) trainerEnrollForm.lookup("#trnNameTxt");
		TextField trnBirthTxt = (TextField) trainerEnrollForm.lookup("#trnBirthTxt");
		TextField trnMobileTxt = (TextField) trainerEnrollForm.lookup("#trnMobileTxt");
		TextField trnAddrTxt1 = (TextField) trainerEnrollForm.lookup("#trnAddrTxt1");
		TextField trnAddrTxt2 = (TextField) trainerEnrollForm.lookup("#trnAddrTxt2");
		TextField trnCareerTxt = (TextField) trainerEnrollForm.lookup("#trnCareerTxt");
		RadioButton trnMenRadio = (RadioButton) trainerEnrollForm.lookup("#trnMenRadio");
		RadioButton trnWomenRadio = (RadioButton) trainerEnrollForm.lookup("#trnWomenRadio");
		ToggleGroup group = new ToggleGroup();
		trnMenRadio.setToggleGroup(group);
		trnWomenRadio.setToggleGroup(group);
		
		String trnId = trnIdTxt.getText();
		String trnPw = trnPwTxt.getText();
		String trnPwComfrim = trnPwComfrimTxt.getText();
		String trnName = trnNameTxt.getText();
		String trnAddr1 = trnAddrTxt1.getText();
		String trnAddr2 = trnAddrTxt2.getText();
		String trnAaddr = trnAddr1 + "/" + trnAddr2;
		
		String birth = trnBirthTxt.getText();
		int trnBirth;
		if (birth.isEmpty()) {
			trnBirth = 0;
		} else {
			trnBirth = Integer.parseInt(birth);
		}

		String mobile = trnMobileTxt.getText();
		int trnMobile;
		if (mobile.isEmpty()) {
			trnMobile = 0;
		} else {
			trnMobile = Integer.parseInt(mobile);
		}
		
		String career = trnCareerTxt.getText();
		int trnCareer;
		
		if (career.isEmpty()) {
			trnCareer = 0;
		} else {
			trnCareer = Integer.parseInt(career);
		}
		
		String trnCode = trnId + trnBirth;
		String trnGender;
		if(trnMenRadio.isSelected()) {
			trnGender = "남";
		} else {
			trnGender = "여";
		}
		
		if (trnId.isEmpty() || trnPw.isEmpty() || trnPwComfrim.isEmpty() || trnName.isEmpty()) {
			CommonService.Msg(" * 필수 입력란을 입력해주세요.");
		} else {
			if (dao.SltTrnId(trnId) == null) {
				if (trnPw.equals(trnPwComfrim)) {
					dao = new CmnTrainerDAO();
					CmnTrainerDTO dto = new CmnTrainerDTO(trnCode, trnName, trnId, trnPw, trnGender, trnBirth,
							trnMobile, trnCareer, trnAaddr);
					if (dao.IstTrn(dto) == 1) {
						CommonService.Msg(trnId + "강사 등록되었습니다.");
						CommonService.WindowClose(trainerEnrollForm);
					} else {
						CommonService.Msg("강사 등록 실패");
					}
				} else {
					CommonService.Msg("비밀번호가 다릅니다.");
				}
			} else {
				CommonService.Msg("중복 체크를 해주세요.");
			}
		}
		
		
	}

	
}