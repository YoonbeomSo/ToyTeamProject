package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trn.Welcome.TrnTbVDTO;


public class CmnPrmScheDAO {
   //TB : PRMSCHE_TB
   private String sql = "";
   private Connection con = null;
   private PreparedStatement ps = null;
   private ResultSet rs = null;
   
   public CmnPrmScheDAO() {
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String user = "KGGYM";
      String password = "oracle1";
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         con = DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   public int IstPrmSche(CmnPrmScheDTO DTO) {
      int result = 0;
      sql = "INSERT INTO PRMSCHE_TB "+
            "(PRMSCHE_Code, "
            + "PRMSCHE_Strdate, "
            + "PRMSCHE_Enddate, "
            + "PRMSCHE_Time, "
            + "PRMSCHE_LimitP, "
            + "PRMSCHE_CurrentP, "
            + "PRMSCHE_Price, "
            + "PRM_Code, "
            + "TRAINER_Code, "
            + "PRMSCHE_Name,"
            + "PRMSCHECODE_Num)"+
            "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
      //            1,2,3,4,5,6,7,8,9,10
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, DTO.getPRMSCHE_Code());
         ps.setDate(2, DTO.getPRMSCHE_Strdate());
         ps.setDate(3, DTO.getPRMSCHE_Enddate());
         ps.setString(4, DTO.getPRMSCHE_Time());
         ps.setInt(5, DTO.getPRMSCHE_LimitP());
         ps.setInt(6, DTO.getPRMSCHE_CurrentP());
         ps.setInt(7, DTO.getPRMSCHE_Price());
         ps.setString(8, DTO.getPRM_Code());
         ps.setString(9, DTO.getTRAINER_Code());
         ps.setString(10, DTO.getPRMSCHE_Name());
         ps.setInt(11, DTO.getPRMSCHECode_Num());
         result = ps.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         try { 
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            e2.printStackTrace();
         }
      }
      return result;
   }
   
   public CmnPrmScheDTO SltPrmScheCode(String MEM_Code) {
      CmnPrmScheDTO tmpdata = null;
      sql = "SELECT * FROM PRMSCHE_TB WHERE MEM_Code = ?";
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, MEM_Code);
         rs = ps.executeQuery();
         if(rs.next()) {
            tmpdata = new CmnPrmScheDTO();
            tmpdata.setPRMSCHE_Code(rs.getString("PRMSCHE_Code"));
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return tmpdata;      
   }
   
   public CmnPrmScheDTO SltPrmScheOne(String PRMSCHE_Code) {
      CmnPrmScheDTO tmpdata = null;
      sql = "SELECT * FROM PRMSCHE_TB WHERE PRMSCHE_Code = ?" ;
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, PRMSCHE_Code);
         rs = ps.executeQuery();
         if(rs.next()) {
            tmpdata = new CmnPrmScheDTO(
               rs.getString("PRMSCHE_Code"),
               rs.getInt("PRMSCHECode_Num"),
               rs.getDate("PRMSCHE_Strdate"),
               rs.getDate("PRMSCHE_Enddate"),
               rs.getString("PRMSCHE_Time"),
               rs.getInt("PRMSCHE_LimitP"),
               rs.getInt("PRMSCHE_CurrentP"),
               rs.getInt("PRMSCHE_Price"),
               rs.getString("PRM_Code"),
               rs.getString("TRAINER_Code"),
               rs.getString("PRMSCHE_Name")
            );
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return tmpdata;      
   }
   
   public int UptPrmSche(CmnPrmScheDTO DTO) {
      int result = 0;
      sql = "UPDATE PRMSCHE_TB SET "
            + "PRMSCHE_Strdate=?, PRMSCHE_Enddate=?, "
            + "PRMSCHE_Time=?, PRMSCHE_LimitP=?, "
            + "PRMSCHE_CurrentP=?, PRMSCHE_Price=?, "
            + "PRM_Code=?, TRAINER_Code=?, PRMSCHE_Name=? "
            + "WHERE PRMSCHE_Code=?";
      try {
         ps = con.prepareStatement(sql);
         ps.setDate(1, DTO.getPRMSCHE_Strdate());
         ps.setDate(2, DTO.getPRMSCHE_Enddate());
         ps.setString(3, DTO.getPRMSCHE_Time());
         ps.setInt(4, DTO.getPRMSCHE_LimitP());
         ps.setInt(5, DTO.getPRMSCHE_CurrentP());
         ps.setInt(6, DTO.getPRMSCHE_Price());
         ps.setString(7, DTO.getPRM_Code());
         ps.setString(8, DTO.getTRAINER_Code());
         ps.setString(9, DTO.getPRMSCHE_Name());
         ps.setString(10, DTO.getPRMSCHE_Code());
         result = ps.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         try { 
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            e2.printStackTrace();
         }
      }
      return result;
   }
   
   public int DltPrmSche(String PRMSCHE_Code) {
      int result = 0;
      sql = "DELETE FROM PRMSCHE_TB WHERE PRMSCHE_Code=?";
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, PRMSCHE_Code);
         result = ps.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         try { 
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            e2.printStackTrace();
         }
      }
      return result;
   }
   
   public ArrayList<CmnPrmScheDTO> SltPrmScheAll(){
      ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
      CmnPrmScheDTO tmpdata = null;
      sql = "SELECT * FROM PRMSCHE_TB";
      try {
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()) {
            tmpdata = new CmnPrmScheDTO(
               rs.getString("PRMSCHE_Code"),
               rs.getInt("PRMSCHECode_Num"),
               rs.getDate("PRMSCHE_Strdate"),
               rs.getDate("PRMSCHE_Enddate"),
               rs.getString("PRMSCHE_Time"),
               rs.getInt("PRMSCHE_LimitP"),
               rs.getInt("PRMSCHE_CurrentP"),
               rs.getInt("PRMSCHE_Price"),
               rs.getString("PRM_Code"),
               rs.getString("TRAINER_Code"),
               rs.getString("PRMSCHE_Name")
            );
            Datalist.add(tmpdata);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return Datalist;
   }
   public ArrayList<CmnPrmScheDTO> SltPrmScheAllbyPrmandTime(String PRM_CODE, String time){
      ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
      CmnPrmScheDTO tmpdata = null;
      sql = "SELECT * FROM PRMSCHE_TB WHERE PRM_CODE = ? AND PRMSCHE_TIME= ?";
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, PRM_CODE);
         ps.setString(1, time);
         rs = ps.executeQuery();
         while(rs.next()) {
            tmpdata = new CmnPrmScheDTO(
               rs.getString("PRMSCHE_CODE"),
               rs.getInt("PRMSCHECode_Num"),
               rs.getDate("PRMSCHE_STRDATE"),
               rs.getDate("PRMSCHE_ENDDATE"),
               rs.getString("PRMSCHE_TIME"),
               rs.getInt("PRMSCHE_LIMITP"),
               rs.getInt("PRMSCHE_CURRENTP"),
               rs.getInt("PRMSCHE_PRICE"),
               rs.getString("PRM_CODE"),
               rs.getString("TRAINER_CODE"),
               rs.getString("PRMSCHE_NAME")
            );
            Datalist.add(tmpdata);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return Datalist;
   }
   
   public ArrayList<CmnPrmScheDTO> SltPrmScheAllbyPrm(String PRM_CODE){
      ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
      CmnPrmScheDTO tmpdata = null;
      sql = "SELECT * FROM PRMSCHE_TB WHERE PRM_CODE = ?";
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, PRM_CODE);
         rs = ps.executeQuery();
         while(rs.next()) {
            tmpdata = new CmnPrmScheDTO(
               rs.getString("PRMSCHE_CODE"),
               rs.getInt("PRMSCHECode_Num"),
               rs.getDate("PRMSCHE_STRDATE"),
               rs.getDate("PRMSCHE_ENDDATE"),
               rs.getString("PRMSCHE_TIME"),
               rs.getInt("PRMSCHE_LIMITP"),
               rs.getInt("PRMSCHE_CURRENTP"),
               rs.getInt("PRMSCHE_PRICE"),
               rs.getString("PRM_CODE"),
               rs.getString("TRAINER_CODE"),
               rs.getString("PRMSCHE_NAME")
            );
            Datalist.add(tmpdata);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return Datalist;
   }
   
   public ArrayList<CmnPrmScheDTO> SltPrmScheAllbyTrn(String TRAINER_Code){
      ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
      CmnPrmScheDTO tmpdata = null;
      sql = "SELECT * FROM PRMSCHE_TB WHERE TRAINER_Code=?";
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, TRAINER_Code);
         rs = ps.executeQuery();
         while(rs.next()) {
            tmpdata = new CmnPrmScheDTO(
               rs.getString("PRMSCHE_Code"),
               rs.getInt("PRMSCHECode_Num"),
               rs.getDate("PRMSCHE_Strdate"),
               rs.getDate("PRMSCHE_Enddate"),
               rs.getString("PRMSCHE_Time"),
               rs.getInt("PRMSCHE_LimitP"),
               rs.getInt("PRMSCHE_CurrentP"),
               rs.getInt("PRMSCHE_Price"),
               rs.getString("PRM_Code"),
               rs.getString("TRAINER_Code"),
               rs.getString("PRMSCHE_Name")
            );
            Datalist.add(tmpdata);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return Datalist;
   }

   public ObservableList<TrnTbVDTO> SltPrmScheAllforTable(){
      ObservableList<TrnTbVDTO> Datalist = FXCollections.observableArrayList();
      TrnTbVDTO tmpTvDtO = null;
      sql = "SELECT * FROM PRMSCHE_TB";
      try {
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()) {
            tmpTvDtO = new TrnTbVDTO(rs.getString("PRMSCHE_Code"), rs.getString("PRMSCHE_Name"),
                  Integer.toString(rs.getInt("PRMSCHE_CurrentP"))
                  );
            Datalist.add(tmpTvDtO);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return Datalist;
   }
   
   public ObservableList<CmnPrmScheDTO> GetPrmScheAll(){
      ObservableList<CmnPrmScheDTO> Datalist = FXCollections.observableArrayList();
      CmnPrmScheDTO prmScheDto;
      sql = "SELECT * FROM PRMSCHE_TB";
      try {
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()) {
            prmScheDto = new CmnPrmScheDTO(rs.getString("PRMSCHE_Code"), rs.getInt("PRMSCHECode_Num"), 
                  rs.getDate("PRMSCHE_Strdate"), rs.getDate("PRMSCHE_Enddate"), rs.getString("PRMSCHE_Time"), 
                  rs.getInt("PRMSCHE_LimitP"), rs.getInt("PRMSCHE_CurrentP"), rs.getInt("PRMSCHE_Price"), 
                  rs.getString("PRM_Code"), rs.getString("TRAINER_Code"), rs.getString("PRMSCHE_Name"));
            Datalist.add(prmScheDto);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
      }
      return Datalist;
   }
   
   public ArrayList<CmnPrmScheDTO> GetPrmScheCode(String PRM_Code) {
      sql = "SELECT * FROM PRMSCHE_TB WHERE PRM_Code = ?";
      ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
      CmnPrmScheDTO tmpdata = null;
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, PRM_Code);
         rs = ps.executeQuery();
         while(rs.next()) {
            tmpdata = new CmnPrmScheDTO();
            tmpdata.setPRMSCHE_Code(rs.getString("PRMSCHE_Code"));
            tmpdata.setPRM_Code(rs.getString("PRM_Code"));
            Datalist.add(tmpdata);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         try {
            if (rs != null)
               rs.close();
            if (ps != null)
               ps.close();
         } catch (SQLException e2) {
            e2.printStackTrace();
         }
      }
      return Datalist;
   }
   public int SltPrmScheCodeMaxNum() {
      int result = 0;
      sql = "SELECT MAX(PRMSCHECode_Num) FROM PRMSCHE_TB";
      try {
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         if(rs.next()) {
            result = rs.getInt("MAX(PRMSCHECode_Num)");

         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if (rs != null)   rs.close();
            if (ps != null)   ps.close();
         } catch (SQLException e2) {
            e2.printStackTrace();
         }
      }
      return result;
   }
   public int SltPrmScheCodeMaxNumbyPrmandTime(String PRM_CODE, String time) {
      int result = 0;
      sql = "SELECT MAX(PRMSCHECode_Num) FROM PRMSCHE_TB WHERE PRM_CODE = ? AND PRMSCHE_TIME= ?";
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, PRM_CODE);
         ps.setString(2, time);
         rs = ps.executeQuery();
         if(rs.next()) {
            result = rs.getInt("MAX(PRMSCHECode_Num)");

         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if (rs != null)   rs.close();
            if (ps != null)   ps.close();
         } catch (SQLException e2) {
            e2.printStackTrace();
         }
      }
      return result;
   }
   
   
   public CmnPrmScheDTO SltPrmScheName(String PRMSCHE_Name) {
      CmnPrmScheDTO tmpdata = null;
      sql = "SELECT * FROM PRMSCHE_TB WHERE PRMSCHE_Name = ?" ;
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, PRMSCHE_Name);
         rs = ps.executeQuery();
         if(rs.next()) {
            tmpdata = new CmnPrmScheDTO(
               rs.getString("PRMSCHE_Code"),
               rs.getInt("PRMSCHECode_Num"),
               rs.getDate("PRMSCHE_Strdate"),
               rs.getDate("PRMSCHE_Enddate"),
               rs.getString("PRMSCHE_Time"),
               rs.getInt("PRMSCHE_LimitP"),
               rs.getInt("PRMSCHE_CurrentP"),
               rs.getInt("PRMSCHE_Price"),
               rs.getString("PRM_Code"),
               rs.getString("TRAINER_Code"),
               rs.getString("PRMSCHE_Name")
            );
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
      return tmpdata;      
   }
   
   public CmnPrmScheDTO SltByTrnCode(String TRAINER_Code) {
		CmnPrmScheDTO tmpdata = null;
		sql = "SELECT * FROM PRMSCHE_TB WHERE TRAINER_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, TRAINER_Code);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPrmScheDTO(
					rs.getString("PRMSCHE_Code"),
					rs.getInt("PRMSCHECode_Num"),
					rs.getDate("PRMSCHE_Strdate"),
					rs.getDate("PRMSCHE_Enddate"),
					rs.getString("PRMSCHE_Time"),
					rs.getInt("PRMSCHE_LimitP"),
					rs.getInt("PRMSCHE_CurrentP"),
					rs.getInt("PRMSCHE_Price"),
					rs.getString("PRM_Code"),
					rs.getString("TRAINER_Code"),
					rs.getString("PRMSCHE_Name")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return tmpdata;		
	}
   
// 프로그램스케줄 코드번호 최대 값 찾기
		public int PrmScheMaxCodeNum() {
			int result = 0;
			sql = "SELECT Max(PRMSCHECODE_NUM) FROM PRMSCHE_TB";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getInt("Max(PRMSCHECODE_NUM)");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return result;
		}
//프로그램별 프로그램 스케쥴 최대값
		public int PrmScheMaxCodeNumbyPrm(String Prm_Code) {
			int result = 0;
			sql = "SELECT Max(PRMSCHECODE_NUM) FROM PRMSCHE_TB WHERE PRM_Code = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, Prm_Code);
				rs = ps.executeQuery();
				if(rs.next()) {
					result = rs.getInt("Max(PRMSCHECODE_NUM)");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)	rs.close();
					if (ps != null)	ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return result;
		}   
   
   
   
}//class end