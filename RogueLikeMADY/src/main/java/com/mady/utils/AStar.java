package com.mady.utils;

import com.mady.utils.entities.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {
    public int[][] returnPath(Node currentNode, Map map) {
        List<Position> path = new ArrayList<>();
        int noCollumn = map.getBASE_WIDTH();
        int noRows = map.getBASE_HEIGHT();
        int[][] result = new int[noRows][noCollumn];
        for (int i = 0; i < noRows; i++) {
            for (int j = 0; j < noCollumn; j++) {
                result[i][j] = -1;
            }
        }
        Node current = currentNode;
        while (current != null) {
            path.add(current.getPosition());
            current = current.getParent();
        }
        Collections.reverse(path);
        int startValue = 0;
        for (Position position : path) {
            result[position.getX()][position.getY()] = startValue;
            startValue++;
        }
        return result;


    }

    public int[][] search(Map map, int cost, Position start, Position end) {
        Node startNode = new Node(null, start);
        Node endNode = new Node(null, end);
        Node currentNode;
        List<Node> yetToVisitList = new ArrayList<>();
        List<Node> visitedList = new ArrayList<>();
        yetToVisitList.add(startNode);

        int outerIterations = 0;
        int maxIterations = (int) Math.pow(((double) map.getBASE_HEIGHT()) / 2, 10);
        Position[] move = new Position[]{new Position(-1, 0), new Position(0, -1), new Position(1, 0), new Position(0, 1)};
        int noColumn = map.getBASE_WIDTH();
        int noRows = map.getBASE_HEIGHT();
        int currentIndex;
        List<Node> children;
        while (yetToVisitList.size() > 0) {

            outerIterations++;
            currentNode = yetToVisitList.get(0);
            currentIndex = 0;
            for (int i = 0; i < yetToVisitList.size(); i++) {
                Node item = yetToVisitList.get(i);
                if (item.getF() < currentNode.getF()) {
                    currentNode = item;
                    currentIndex = i;
                }

            }
            if (outerIterations > maxIterations) {
                System.out.println("giving up on pathfinding");
                return returnPath(currentNode, map);
            }
            yetToVisitList.remove(currentIndex);
            visitedList.add(currentNode);
            if (currentNode.equals(endNode)) {

                return returnPath(currentNode, map);
            }
            children = new ArrayList<>();
            for (Position newPosition : move) {
                Position nodePosition = new Position(currentNode.getPosition().getX() + newPosition.getX(),
                        currentNode.getPosition().getY() + newPosition.getY());
                if (nodePosition.getX() > noRows - 1 || nodePosition.getX() < 0
                        || nodePosition.getY() > noColumn - 1
                        || nodePosition.getY() < 0) {
                    continue;

                }
                if (!map.getMap()[nodePosition.getX()][nodePosition.getY()].isMap()
                        && !map.getMap()[nodePosition.getX()][nodePosition.getY()].isPath()) {
                    continue;
                }
                Node newNode = new Node(currentNode, nodePosition);
                children.add(newNode);

            }
            for (Node child : children) {
                List<Node> tempChild = new ArrayList<>();
                for (Node n : visitedList) {
                    if (n.equals(child)) {
                        tempChild.add(n);
                    }

                }
                if (tempChild.size() > 0) {
                    continue;
                }

                child.setG(currentNode.getG() + cost);
                child.setH((int) (Math.pow(child.getPosition().getX() - endNode.getPosition().getX(), 2) +
                        Math.pow(child.getPosition().getY() - endNode.getPosition().getY(), 2)));
                child.setF(child.getG() + child.getH());

                List<Node> tempChild2 = new ArrayList<>();


                for (Node n : yetToVisitList) {
                    if (n.equals(child) && n.getG() < child.getG()) {
                        tempChild2.add(n);
                    }
                }

                if (tempChild2.size() > 0) {
                    continue;
                }
                yetToVisitList.add(child);
            }
        }
        return null;
    }

}