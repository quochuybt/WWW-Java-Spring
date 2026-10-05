package iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.servlet;

import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.dao.DanhSachTinTucQuanLy;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model.DanhMuc;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model.TinTuc;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.util.DBUtil;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/danh-sach-tin-tuc")
public class DanhSachTinTucServlet extends HttpServlet {

    @Resource(name = "jdbc/quanlytintuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy quanLy;

    @Override
    public void init() {
        quanLy = new DanhSachTinTucQuanLy(
                new DBUtil(dataSource)
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String maDM = request.getParameter("maDM");

        List<TinTuc> danhSachTin;

        if (maDM == null || maDM.isBlank()) {
            danhSachTin = quanLy.getAllTinTuc();
        } else {
            danhSachTin = quanLy.getTinTucByDanhMuc(
                    Integer.parseInt(maDM)
            );
        }

        List<DanhMuc> danhSachDanhMuc =
                quanLy.getAllDanhMuc();

        request.setAttribute(
                "danhSachTin",
                danhSachTin
        );

        request.setAttribute(
                "danhSachDanhMuc",
                danhSachDanhMuc
        );

        request.getRequestDispatcher(
                "/DanhSachTinTuc.jsp"
        ).forward(request, response);
    }
}