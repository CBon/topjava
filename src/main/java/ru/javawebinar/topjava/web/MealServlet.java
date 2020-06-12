package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    private InMemoryRepository inMemoryRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        inMemoryRepository = new InMemoryRepository();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            LOG.info("get all meals");
            List<MealTo> mealsToList = MealsUtil.filteredByStreams(new ArrayList<>(inMemoryRepository.getAll()), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("mealToList", mealsToList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mealList.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            inMemoryRepository.delete(id);
            response.sendRedirect("meals");
        } else if (action.equals("create")) {
            LOG.info("create");
            final Meal meal = new Meal(LocalDateTime.now(), "", 1000);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealForm.jsp").forward(request, response);
        } else if (action.equals("update")) {
            int id = getId(request);
            final Meal meal = inMemoryRepository.get(id);
            LOG.info("update {}", id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealForm.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        inMemoryRepository.save(meal);
        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
