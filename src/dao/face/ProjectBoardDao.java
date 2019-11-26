package dao.face;

import java.util.List;

import dto.ProjectBoard;
import util.Paging;

public interface ProjectBoardDao {
	
	public int selectCntAll();

	public List<ProjectBoard> selectAll(Paging paging);

	public ProjectBoard selectBoardByProjectno(ProjectBoard projectBoard);
}
