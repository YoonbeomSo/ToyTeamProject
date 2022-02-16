package admin.exProgramMgt;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import common.CommonService;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



public class ExProgramMgtController implements Initializable{
	private Parent exProgramMgtForm;
	private ExProgramMgtService exProgramSvc;
	private String selectData;
	private ExProTable codeTable;
	private ObservableList<String> allProgram;
	@FXML private ComboBox<String> kindComboBox;
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	
	@FXML private ListView<String> programListView;
	@FXML private TableView<ExProTable> exProgramTableView;
	@FXML private TableColumn<ExProTable, String> programName;
	@FXML private TableColumn<ExProTable, String> code;
	@FXML private TableColumn<ExProTable, String> trainerName;
	@FXML private TableColumn<ExProTable, Integer> limtPerson;
	@FXML private TableColumn<ExProTable, Integer> currentPerson;
	@FXML private TableColumn<ExProTable, Date> strDate;
	@FXML private TableColumn<ExProTable, Date> endDate;
	@FXML private TableColumn<ExProTable, Integer> price;
	@FXML private TableColumn<ExProTable, String> timeC;

	
	/*
	 * 1.ex프로그램 combobox 선택 후 수정 연동
	 * 2.아무것도 없을때 수정 누르면 에러처리
	 * 3.DatePicker에 날짜 뜨게
	*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		exProgramSvc = new ExProgramMgtService();
		//리스트 창 
		exProgramSvc.listUp(this.programListView);
		
		//테이블 창
		programName.setCellValueFactory(new PropertyValueFactory<>("programName"));
		code.setCellValueFactory(new PropertyValueFactory<>("code"));
		trainerName.setCellValueFactory(new PropertyValueFactory<>("trainerName"));
		programName.setCellValueFactory(new PropertyValueFactory<>("programName"));
		limtPerson.setCellValueFactory(new PropertyValueFactory<>("limtPerson"));
		currentPerson.setCellValueFactory(new PropertyValueFactory<>("currentPerson"));
		strDate.setCellValueFactory(new PropertyValueFactory<>("strDate"));
		endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		timeC.setCellValueFactory(new PropertyValueFactory<>("timeC"));
		
		exProgramSvc.tableUp(exProgramTableView);
		
		
		//수정창
		allProgram = exProgramSvc.getAllProgram();
		kindComboBox.setItems(allProgram);
		
		//listview 클릭 시
		programListView.setOnMouseClicked(new EventHandler<MouseEvent>() { 
			
			@Override public void handle(MouseEvent event) { 
				selectData = programListView.getSelectionModel().getSelectedItem(); 
				System.out.println(selectData);
				exProgramSvc.setSelectData(selectData);
				}
			});
		
		exProgramTableView.setOnMouseClicked(new EventHandler<MouseEvent>() { 
			@Override public void handle(MouseEvent event) { 
				codeTable = exProgramTableView.getSelectionModel().getSelectedItem();
				exProgramSvc.setCodeTable(codeTable);
				exProgramSvc.modifyTableUp(exProgramMgtForm);
				
				}
			});
	}
	
	public void setExProgramMgtForm(Parent exProgramMgtForm) {
		this.exProgramMgtForm = exProgramMgtForm;
	}
	
	
	
	// 등록 버튼 클릭 시
	public void insertProc() {
		System.out.println("프로그램 등록");
		exProgramSvc.insertProc(exProgramMgtForm);
		this.allProgram = exProgramSvc.getAllProgram();
		kindComboBox.setItems(this.allProgram);
	}
	
	// 삭제 버튼 클릭 시
	public void deleteProc() {
		System.out.println("프로그램 삭제");
		exProgramSvc.deleteProc(exProgramMgtForm);
		this.allProgram = exProgramSvc.getAllProgram();
		kindComboBox.setItems(this.allProgram);
	}
	
	
	// 세부 수정 버튼 클릭 시
	public void exProgramModifyProc() {
		System.out.println("프로그램 수정");
		exProgramSvc.exProgramModifyProc(exProgramMgtForm);
	}
	
	// 세부 삭제 버튼 클릭 시
	public void exProgramDeleteProc() {
		System.out.println("프로그램 삭제");
		exProgramSvc.exProgramDeleteProc(exProgramMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void exProgramCancleProc() {
		CommonService.WindowClose(exProgramMgtForm);
	}


}
