package iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.servlet;

import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.dao.DanhSachTinTucQuanLy;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model.DanhMuc;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model.TinTuc;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.util.DBUtil;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/tin-tuc-form")
public class TinTucFormServlet extends HttpServlet {

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

        List<DanhMuc> danhSachDanhMuc =
                quanLy.getAllDanhMuc();

        request.setAttribute(
                "danhSachDanhMuc",
                danhSachDanhMuc
        );

        request.getRequestDispatcher(
                "/TinTucForm.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int maTinTuc =
                Integer.parseInt(
                        request.getParameter("maTinTuc")
                );

        String tieuDe =
                request.getParameter("tieuDe");

        String noiDung =
                request.getParameter("noiDungTinTuc");

        String lienKet =
                request.getParameter("lienKet");

        int maDM =
                Integer.parseInt(
                        request.getParameter("maDM")
                );

        TinTuc tinTuc = new TinTuc(
                maTinTuc,
                tieuDe,
                noiDung,
                lienKet,
                maDM
        );

        try {

            quanLy.addTinTuc(tinTuc);

            response.sendRedirect(
                    request.getContextPath()
                            + "/danh-sach-tin-tuc"
            );

        } catch (RuntimeException e) {

            request.setAttribute(
                    "error",
                    "Không thể thêm tin tức. Mã tin tức có thể đã tồn tại."
            );

            request.setAttribute(
                    "danhSachDanhMuc",
                    quanLy.getAllDanhMuc()
            );

            request.getRequestDispatcher(
                    "/TinTucForm.jsp"
            ).forward(request, response);
        }
    }
}