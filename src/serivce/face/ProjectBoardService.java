package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ProjectBoard;
import util.Paging;

public interface ProjectBoardService {

	public Paging getPaging(HttpServletRequest req);

	public List<ProjectBoard> getBoardList(Paging paging);

	public ProjectBoard getProjectBoardno(HttpServletRequest req);

	public ProjectBoard view(ProjectBoard projectBoard);

}
