package goodee.three.board.service;

import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import goodee.three.board.mapper.BoardFileMapper;
import goodee.three.board.mapper.BoardMapper;
import goodee.three.board.mapper.CommentMapper;
import goodee.three.board.vo.Board;
import goodee.three.board.vo.BoardFile;
import goodee.three.board.vo.BoardForm;
import goodee.three.board.vo.Page;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired private BoardMapper boardMapper;
	@Autowired private BoardFileMapper boardFileMapper;
	@Autowired private CommentMapper commentMapper;
	
	
	@Transactional
	@Override
	//글 삭제
	public int deleteBoard(Board board) {
		System.out.println("delete board ck:" + board);
		Board onBoard = boardMapper.selectBoardOne(board.getBoardNo());
		System.out.println("on:"+onBoard);
		
		BoardFile boardFile = new BoardFile();
		boardFile = boardFileMapper.selectBoardFileOne(board.getBoardNo());
		System.out.println("boardFile!!" + boardFile);
		/* 비밀번호 틀릴경우 */
		System.out.println("ck:"+onBoard.getBoardPw());
		System.out.println("ck2:"+board.getBoardPw());
		if(onBoard.getBoardPw().equals(board.getBoardPw())) {
			System.out.println("비번 확인");
			
			/* 댓글 전체 삭제 */
				int commentRow = commentMapper.deleteAll(board.getBoardNo());

				int fileRow = boardFileMapper.deleteBoardFile(board.getBoardNo());
				System.out.println("c,f row ck>>"+commentRow+","+fileRow);
			/* 파일 삭제 */
				String path = "C:\\spring\\springWorkspace\\homework-board\\src\\main\\webapp\\upload\\"
							 +boardFile.getFileName();
					 
				System.out.println("path>>" + path);
				
				File file = new File(path);
				if (file.exists()) {
					System.out.println("file 있슴");
					 file.delete();				
				} else {
					System.out.println("없슴");
				}
				
				//board db 삭제
				return boardMapper.deleteBoard(board);
		}else {
			System.out.println("비밀번호 수정 바람");
			return 0;
		}
		
	}

	@Override//수정
	public int updateBoard(BoardForm boardForm) {
		System.out.println("update Service ck:" + boardForm);
		BoardFile boardFile = boardFileMapper.selectBoardFileOne(boardForm.getBoardNo());
		System.out.println("file 확인 "+boardFile);
		String path = "C:\\spring\\springWorkspace\\homework-board\\src\\main\\webapp\\upload\\"
				 +boardFile.getFileName();
		File file = new File(path);
		if(file.exists()) {
			file.delete();
			System.out.println("file 삭제 완료?");
		}
		
		MultipartFile mf = boardForm.getBoardFile();
		System.out.println("새 파일 >>"+mf);
		//새롭게 업로드 하는 파일
		String contentType = mf.getContentType(); // 파일 타입
		String originName = mf.getOriginalFilename(); //
		long size = mf.getSize();
		String uploadPath = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload").toUriString();
		String extension = originName.substring(originName.lastIndexOf(".") + 1);
		String fileName = UUID.randomUUID().toString().replace("-", "")+"." + extension;

		// 테이블에 저장
		boardFile.setBoardNo(boardForm.getBoardNo());
		boardFile.setFileName(fileName);
		boardFile.setExtension(extension);
		boardFile.setContentType(contentType);
		boardFile.setSize(size);
		boardFile.setOriginName(originName);
		System.out.println("파일 ck:" + boardFile);
		//파일 추가
		int insFileRow = boardFileMapper.updateBoardFileOne(boardFile);
		System.out.println("새로 업로드 할 파일 ck>>"+insFileRow);
		
		//board 내용 수정
		boardMapper.updateBoard(boardForm);
		//파일 저장
		try {
			mf.transferTo(
					new File("C:\\spring\\springWorkspace\\homework-board\\src\\main\\webapp\\upload\\" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return boardMapper.updateBoard(boardForm);
	}

	@Override//상세보기
	public Board selectBoradOne(int boardNo) {
		System.out.println("service One No:" + boardNo);

		BoardFile boardFile = new BoardFile();

		Board board = boardMapper.selectBoardOne(boardNo);
		// board.boardfile
		boardFile = boardFileMapper.selectBoardFileOne(boardNo);
		System.out.println("boardFile :>:" + boardFile);
		board.setBoardFile(boardFile);

		return boardMapper.selectBoardOne(boardNo);
	}

	@Override//추가하기
	public void insertBoard(BoardForm boardForm) {
		System.out.println("service insert ");
		// boardForm --> Board,BoardFile 로 분리작업
		// 1. Board
		Board board = new Board();
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardPw(boardForm.getBoardPw());
		board.setBoardContent(boardForm.getBoardContent());
		board.setBoardUser(boardForm.getBoardUser());
		boardMapper.insertBoard(board);
		System.out.println("service Form:" + boardMapper);
		System.out.println("No;" + board.getBoardNo());
		// boardMapper.insertBoardFile(boardFile);
		// System.out.println("insert board ck:"+board);

		// 2. BoardFile
		MultipartFile mf = boardForm.getBoardFile();
		String contentType = mf.getContentType(); // 파일 타입
		String originName = mf.getOriginalFilename(); //
		long size = mf.getSize();
		System.out.println("contentType=" + contentType);
		System.out.println("originName=" + originName);
		System.out.println("size=" + size);
		String uploadPath = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload").toUriString(); //
		System.out.println("path:" + uploadPath);

		String extension = originName.substring(originName.lastIndexOf(".") + 1);
		String fileName = UUID.randomUUID().toString().replace("-", "")/* +"." */ + extension;

		// 테이블에 저장
		BoardFile boardFile = new BoardFile();
		boardFile.setBoardNo(board.getBoardNo());
		boardFile.setFileName(fileName);
		boardFile.setExtension(extension);
		boardFile.setContentType(contentType);
		boardFile.setSize(size);
		boardFile.setOriginName(originName);
		System.out.println("ck:" + boardFile);

		boardFileMapper.insertBoardFile(boardFile);
		// 파일로 저장
		try {
			mf.transferTo(
					new File("C:\\spring\\springWorkspace\\homework-board\\src\\main\\webapp\\upload\\" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	@Override	//리스트 출력
	public Map<String, Object> selectBoardList(int currentPage, int rowPerPage, String searchWord) {
		System.out.println("service List ck");

		rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		Page page = new Page();
		page.setBeginRow(beginRow);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		System.out.println("Service page ck:" + page);

		List<Board> list = boardMapper.selectBoardList(page);
		int totalRow = boardMapper.selectCount(page);
		System.out.println("전체 행:" + totalRow);
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
			lastPage = (totalRow / rowPerPage) + 1;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("list", list);
		map.put("lastPage", lastPage);
		map.put("totalRow", totalRow);
		System.out.println("service map ck:" + map);

		return map;
	}

}
