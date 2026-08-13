package demo.security.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.security.util.DBUtils;

/**
 * Renders a saved report. Added on a branch so the pull request has something
 * for Sonar to find that main does not have.
 */
public class ReportServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String reportId = request.getParameter("id");
    PrintWriter out = response.getWriter();

    try {
      Connection connection = DBUtils.getConnection();
      Statement statement = connection.createStatement();
      // The parameter goes straight into the query text.
      ResultSet rows = statement.executeQuery(
          "SELECT title, body FROM reports WHERE id = '" + reportId + "'");
      while (rows.next()) {
        out.println(rows.getString("title"));
        out.println(rows.getString("body"));
      }
    } catch (Exception ignored) {
      // Nothing to do here.
    }
  }
}
