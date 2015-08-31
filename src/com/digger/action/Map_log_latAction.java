package com.digger.action;

import com.digger.model.Map_log_lat;
import com.digger.model.Pageinfo;
import com.digger.service.IMap_log_latService;
import com.digger.service.impl.Map_log_latService;
import com.digger.util.ConvertCharacter;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Map_log_latAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	@Override
	public void validate() {
		super.validate();
	}

	private Map_log_lat map_log_lat;
	private IMap_log_latService map_log_latService;
	private String[] map;
	private String place_name;
	private String ranks;
	private String type;

	public void setMap_log_lat(Map_log_lat map_log_lat) {
		this.map_log_lat = map_log_lat;
	}

	public Map_log_lat getMap_log_lat() {
		return map_log_lat;
	}

	public Map_log_latAction() {
		map_log_latService = new Map_log_latService();

	}

	public void setMap(String[] map) {
		this.map = map;
	}

	public String[] getMap() {
		return map;
	}

	public String Ranks() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();// 获得浏览器的请求对象
		ArrayList<Map_log_lat> Rank = new ArrayList<Map_log_lat>();
		String sql = " where ranks=2;";
		System.out.println(sql);
		Map_log_lat map = map_log_latService.selectMap(sql);
		session.setAttribute("maps", Rank);
		session.setAttribute("map", map);
		session.setAttribute("location", map);
		ArrayList<Map_log_lat> Ranks = map_log_latService.getRanks(3);//
		session.setAttribute("Map_info", Ranks);//
		return SUCCESS;
	}

	public String select() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("map");
		session.removeAttribute("mapa");
		session.removeAttribute("mapaa");
		session.removeAttribute("mapaaa");
		session.removeAttribute("Map_info");
		session.removeAttribute("maps");
		session.removeAttribute("maperror");
		session.removeAttribute("location");
		Map_log_lat Map = new Map_log_lat();
		String sql;
		String map = "map";
		String name = new ConvertCharacter().Convert(map_log_lat
				.getPlace_name());
		String type = new ConvertCharacter().Convert(map_log_lat.getType());
		int i, j;
		j = map_log_lat.getRanks();
		for (i = j; i > 1; i--) {
			sql = " where ranks='" + i + "' and place_name='" + name + "';";
			System.out.println(sql);
			Map_log_lat a = map_log_latService.selectMap(sql);
			name = a.getLast_place();
			session.setAttribute(map, a);
			map += "a";
		}
		int m = j + 1;
		if (m < 5) {
			ArrayList<Map_log_lat> Ranks = map_log_latService.getRanks(m);//
			session.setAttribute("Map_info", Ranks);
		}
		String place = new ConvertCharacter().Convert(map_log_lat
				.getPlace_name());
		if (type.equals("nothing")) {
			sql = " where ranks='" + j + "' and place_name='" + place + "';";
			Map = map_log_latService.selectMap(sql);
			session.setAttribute("location", Map);
			ArrayList<Map_log_lat> Typess = new ArrayList<Map_log_lat>();
			session.setAttribute("maps", Typess);
			return SUCCESS;
		} else {
			sql = " where ranks='" + j + "' and place_name='" + place + "';";
			Map = map_log_latService.selectMap(sql);
			session.setAttribute("location", Map);
			String mysql = " where type='" + type + "'and address like'%"
					+ place + "%';";
			ArrayList<Map_log_lat> Types = map_log_latService.getType(mysql);
			System.out.println(Types.size());
			if (Types.size() != 0) {
				session.setAttribute("maps", Types);
				return SUCCESS;
			} else {
				ArrayList<Map_log_lat> Typess = new ArrayList<Map_log_lat>();
				session.setAttribute("maperror", "此区域没有你要找的地点！");
				session.setAttribute("maps", Typess);
				return ERROR;
			}
		}

	}

	public String selectMap() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("maperror");
		if (map_log_lat.getAddress().equals("")) {
			session.setAttribute("maperror", "你输入空值，不给于查询！");
			return ERROR;
		}
		String sql = " where place_name like'%" + map_log_lat.getAddress()
				+ "%';";
		map_log_lat = map_log_latService.selectMap(sql);
		if (map_log_lat != null) {
			session.setAttribute("location", map_log_lat);
			ArrayList<Map_log_lat> Rank = map_log_latService.getType(sql);
			session.setAttribute("maps", Rank);
			return SUCCESS;
		} else {
			session.setAttribute("maperror", "您查找的地方还未添加到数据库！请搜索相关联的地方！");
			return ERROR;
		}

	}

	public String deleteMap() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.removeAttribute("delete");
		Map_log_lat map = map_log_latService.selectId(map_log_lat.getMap_id());
		map_log_latService.deleteMap(map);
		request.setAttribute("delete", "删除成功！");
		return SUCCESS;
	}

	public String selectAll() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得当前页和每页的记录数
		String name = (String) request.getSession().getAttribute("place_name");
		String types = (String) request.getSession().getAttribute("types");
		String rank = (String) request.getSession().getAttribute("rank");
		System.out.println(rank);
		System.out.println(name);
		System.out.println(types);
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		int v_currPage = 0;
		int v_pageSize = 0;
		String sql = " ";
		if (currentPage == null) {
			v_currPage = 1;
		} else {
			v_currPage = Integer.parseInt(currentPage);
		}

		if (pageSize == null) {
			v_pageSize = 3;
		} else {
			v_pageSize = Integer.parseInt(pageSize);
		}
		Pageinfo pageinfo = new Pageinfo();

		if (name == null) {
			if (types == null) {
				if (rank == null) {
					sql = " ";
					System.out.println("fgfdagasd");
					pageinfo = map_log_latService.queryAllmap(v_currPage,
							v_pageSize, sql);
				} else {
					if (rank.equals("0")) {
						sql = " ";
						pageinfo = map_log_latService.queryAllmap(v_currPage,
								v_pageSize, sql);
					} else {
						sql = " where ranks=" + rank;
						pageinfo = map_log_latService.queryAllmap(v_currPage,
								v_pageSize, sql);
					}
				}
			} else {
				if (rank == null) {
					if (types.equals("")) {
						sql = " ";
						pageinfo = map_log_latService.queryAllmap(v_currPage,
								v_pageSize, sql);
					} else {
						sql = " where type like '%" + types + "%'";
						pageinfo = map_log_latService.queryAllmap(v_currPage,
								v_pageSize, sql);
					}
				} else {
					if (types.equals("")) {
						if (rank.equals("0")) {
							sql = " ";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							sql = " where type ranks=" + rank;
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						}
					} else {
						if (rank.equals("0")) {
							sql = " where type like '%" + types + "%'";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							sql = " where type like '%" + types
									+ "%' and ranks=" + rank;
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						}
					}
				}
			}
		} else {
			if (name.equals("")) {
				if (types == null) {
					if (rank == null) {
						sql = " ";
						pageinfo = map_log_latService.queryAllmap(v_currPage,
								v_pageSize, sql);
					} else {
						if (rank.equals("0")) {
							sql = " ";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							sql = " where type ranks=" + rank;
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						}
					}
				} else {
					if (types.equals("")) {
						if (rank == null) {
							sql = " ";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							if (rank.equals("0")) {
								sql = " ";
								pageinfo = map_log_latService.queryAllmap(
										v_currPage, v_pageSize, sql);
							} else {
								sql = " where ranks=" + rank;
								pageinfo = map_log_latService.queryAllmap(
										v_currPage, v_pageSize, sql);
							}
						}
					} else {
						if (rank == null) {
							sql = " where type like '%" + types + "%'";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							if (rank.equals("0")) {
								sql = " where type like '%" + types + "%'";
								pageinfo = map_log_latService.queryAllmap(
										v_currPage, v_pageSize, sql);
							} else {
								sql = " where type like '%" + types
										+ "%' and rank=" + rank;
								pageinfo = map_log_latService.queryAllmap(
										v_currPage, v_pageSize, sql);
							}
						}
					}
				}
			} else {
				if (types == null) {
					if (rank == null) {
						sql = " where place_name like '%" + name + "%'";
						pageinfo = map_log_latService.queryAllmap(v_currPage,
								v_pageSize, sql);
					} else {
						if (rank.equals("0")) {
							sql = " where place_name like '%" + name + "%'";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							sql = " where place_name like '%" + name
									+ "%' and rank=" + rank;
						}
					}
				} else {
					if (types.equals("")) {
						if (rank == null) {
							sql = " where place_name like '%" + name + "%'";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							if (rank.equals("0")) {
								sql = " where place_name like '%" + name + "%'";
								pageinfo = map_log_latService.queryAllmap(
										v_currPage, v_pageSize, sql);
							} else {
								sql = " where place_name like '%" + name
										+ "%' and rank=" + rank;
							}
						}
					} else {
						if (rank == null) {
							sql = " where place_name like '%" + name
									+ "%' and type like '%" + types + "%'";
							pageinfo = map_log_latService.queryAllmap(
									v_currPage, v_pageSize, sql);
						} else {
							if (rank.equals("0")) {
								sql = " where place_name like '%" + name
										+ "%' and type like '%" + types + "%'";
								pageinfo = map_log_latService.queryAllmap(
										v_currPage, v_pageSize, sql);
							} else {
								sql = " where place_name like '%" + name
										+ "%' and type like'%" + types
										+ "%'and ranks=" + rank;
								pageinfo = map_log_latService.queryAllmap(
										v_currPage, v_pageSize, sql);
							}
						}
					}
				}
			}
		}
		request.setAttribute("pageinfo", pageinfo);
		return SUCCESS;
	}

	public String selectId() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		map_log_lat = map_log_latService.selectId(map_log_lat.getMap_id());
		session.setAttribute("map_Id", map_log_lat);
		return SUCCESS;
	}

	public String select_map() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = getPlace_name();
		System.out.println(name);
		String sql;
		String types = getType();
		System.out.println(types);
		String rank = getRanks();
		Pageinfo pageinfo = null;
		if (name.equals("")) {
			if (types.equals("")) {
				if (rank.equals("0")) {
					sql = " ";
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				} else {
					sql = " where ranks=" + rank;
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				}
			} else {
				if (rank.equals("0")) {
					sql = " where type like '%" + types + "%'";
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				} else {
					sql = " where type like '%" + types + "%' and ranks="
							+ rank;
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				}
			}
		} else {
			if (types.equals("")) {
				if (rank.equals("0")) {
					sql = " where place_name like '%" + name + "%'";
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				} else {
					sql = " where place_name like '%" + name + "%'and ranks="
							+ rank;
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				}
			} else {
				if (rank.equals("0")) {
					sql = " where place_name like '%" + name
							+ "%' and type like '%" + types + "%'";
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				} else {
					sql = " where place_name like '%" + name
							+ "%' and type like'%" + types + "%'and ranks="
							+ rank;
					pageinfo = map_log_latService.queryAllmap(1, 3, sql);
				}
			}
		}
		request.setAttribute("pageinfo", pageinfo);
		request.getSession().setAttribute("place_name", name);
		request.getSession().setAttribute("types", types);
		request.getSession().setAttribute("rank", rank);
		return "select_map";
	}

	public String deleteAll() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.removeAttribute("delete");
		String sql;
		for (int i = 0; i < getMap().length; i++) {
			sql = " where map_id=" + getMap()[i];
			Map_log_lat map = map_log_latService.selectMap(sql);
			map_log_latService.deleteMap(map);
		}
		request.setAttribute("delete", "删除成功！");
		return "deleteAll";
	}

	public void setPlace_name(String place_name) {
		// place_name=new ConvertCharacter().Convert(place_name);
		this.place_name = place_name;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setRanks(String ranks) {
		this.ranks = ranks;
	}

	public String getRanks() {
		return ranks;
	}

	public void setType(String type) {
		// type=new ConvertCharacter().Convert(type);
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
