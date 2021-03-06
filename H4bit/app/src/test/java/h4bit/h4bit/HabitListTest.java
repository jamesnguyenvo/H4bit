package h4bit.h4bit;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.util.Arrays;

import h4bit.h4bit.Models.Habit;
import h4bit.h4bit.Models.HabitList;
import h4bit.h4bit.Models.User;
import h4bit.h4bit.Views.MainHabitActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * HabitListTest
 * Version 1.0
 * december 1st 2017
 * Copyright 2017 Team 32, CMPUT 301, University of Alberta - All Rights Reserved.
 */
public class HabitListTest{

    @Test
    public void testAddHabit() {
        HabitList habits = new HabitList();
        boolean[] sched = new boolean[7];
        Arrays.fill(sched, true);
        User user1 = new User();
        Habit habit1 = new Habit("name1", "comment1", sched,user1.getUsername());
        habits.addHabit(habit1);
        assertTrue(habits.hasHabit(habit1));
    }
    @Test
    public void testHasHabit() {
        HabitList habits = new HabitList();
        boolean[] sched = new boolean[7];
        Arrays.fill(sched, true);
        User user1 = new User();
        Habit habit1 = new Habit("name1", "comment1", sched,user1.getUsername());
        assertFalse(habits.hasHabit(habit1));
        habits.addHabit(habit1);
        assertTrue(habits.hasHabit(habit1));
    }
    @Test
    public void testDeleteHabit() {
        HabitList habits = new HabitList();
        boolean[] sched = new boolean[7];
        Arrays.fill(sched, true);
        User user1 = new User();
        Habit habit1 = new Habit("name1", "comment1", sched,user1.getUsername());
        habits.addHabit(habit1);
        habits.deleteHabit(habit1);
        assertFalse(habits.hasHabit(habit1));
    }
    @Test
    public void testGetHabit(){
        HabitList habits = new HabitList();
        boolean[] sched = new boolean[7];
        Arrays.fill(sched, true);
        User user1 = new User();
        Habit habit1 = new Habit("name1", "comment1", sched, user1.getUsername());
        habits.addHabit(habit1);
        Habit ReturnedHabit = habits.getHabit(0);
        assertEquals(habit1, ReturnedHabit);







    }



}
