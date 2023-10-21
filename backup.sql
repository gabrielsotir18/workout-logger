--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9 (Homebrew)
-- Dumped by pg_dump version 14.9 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: exercises; Type: TABLE; Schema: public; Owner: gabrielsotir
--

CREATE TABLE public.exercises (
    exercise_id integer NOT NULL,
    name character varying(100) NOT NULL,
    description text
);


ALTER TABLE public.exercises OWNER TO gabrielsotir;

--
-- Name: exercises_exercise_id_seq; Type: SEQUENCE; Schema: public; Owner: gabrielsotir
--

CREATE SEQUENCE public.exercises_exercise_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exercises_exercise_id_seq OWNER TO gabrielsotir;

--
-- Name: exercises_exercise_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: gabrielsotir
--

ALTER SEQUENCE public.exercises_exercise_id_seq OWNED BY public.exercises.exercise_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: gabrielsotir
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    email character varying(100) NOT NULL
);


ALTER TABLE public.users OWNER TO gabrielsotir;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: gabrielsotir
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO gabrielsotir;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: gabrielsotir
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: workoutlogs; Type: TABLE; Schema: public; Owner: gabrielsotir
--

CREATE TABLE public.workoutlogs (
    log_id integer NOT NULL,
    user_id integer,
    exercise_id integer,
    sets integer NOT NULL,
    reps integer NOT NULL,
    weight numeric(5,2) NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.workoutlogs OWNER TO gabrielsotir;

--
-- Name: workoutlogs_log_id_seq; Type: SEQUENCE; Schema: public; Owner: gabrielsotir
--

CREATE SEQUENCE public.workoutlogs_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workoutlogs_log_id_seq OWNER TO gabrielsotir;

--
-- Name: workoutlogs_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: gabrielsotir
--

ALTER SEQUENCE public.workoutlogs_log_id_seq OWNED BY public.workoutlogs.log_id;


--
-- Name: exercises exercise_id; Type: DEFAULT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.exercises ALTER COLUMN exercise_id SET DEFAULT nextval('public.exercises_exercise_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Name: workoutlogs log_id; Type: DEFAULT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.workoutlogs ALTER COLUMN log_id SET DEFAULT nextval('public.workoutlogs_log_id_seq'::regclass);


--
-- Data for Name: exercises; Type: TABLE DATA; Schema: public; Owner: gabrielsotir
--

COPY public.exercises (exercise_id, name, description) FROM stdin;
1	bench press	Lay on a flat bench and load a barbell with plates
3	squat	Place a barbell in the squat rack and load it with weights
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: gabrielsotir
--

COPY public.users (user_id, username, password, email) FROM stdin;
1	gabriel	capdecal70	gabrielsotir18@yahoo.com
2	admin	admin	admin@yahoo.com
\.


--
-- Data for Name: workoutlogs; Type: TABLE DATA; Schema: public; Owner: gabrielsotir
--

COPY public.workoutlogs (log_id, user_id, exercise_id, sets, reps, weight, date) FROM stdin;
1	1	1	3	10	80.00	2023-07-10
2	1	1	10	10	35.00	2023-12-27
3	1	1	10	10	35.00	2023-12-27
\.


--
-- Name: exercises_exercise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gabrielsotir
--

SELECT pg_catalog.setval('public.exercises_exercise_id_seq', 4, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gabrielsotir
--

SELECT pg_catalog.setval('public.users_user_id_seq', 3, true);


--
-- Name: workoutlogs_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gabrielsotir
--

SELECT pg_catalog.setval('public.workoutlogs_log_id_seq', 3, true);


--
-- Name: exercises exercises_name_key; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.exercises
    ADD CONSTRAINT exercises_name_key UNIQUE (name);


--
-- Name: exercises exercises_pkey; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.exercises
    ADD CONSTRAINT exercises_pkey PRIMARY KEY (exercise_id);


--
-- Name: exercises unique_exercises; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.exercises
    ADD CONSTRAINT unique_exercises UNIQUE (name);


--
-- Name: users unique_username; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_username UNIQUE (username);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: workoutlogs workoutlogs_pkey; Type: CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.workoutlogs
    ADD CONSTRAINT workoutlogs_pkey PRIMARY KEY (log_id);


--
-- Name: idx_workoutlogs_user_exercise; Type: INDEX; Schema: public; Owner: gabrielsotir
--

CREATE INDEX idx_workoutlogs_user_exercise ON public.workoutlogs USING btree (user_id, exercise_id);


--
-- Name: workoutlogs workoutlogs_exercise_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.workoutlogs
    ADD CONSTRAINT workoutlogs_exercise_id_fkey FOREIGN KEY (exercise_id) REFERENCES public.exercises(exercise_id);


--
-- Name: workoutlogs workoutlogs_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gabrielsotir
--

ALTER TABLE ONLY public.workoutlogs
    ADD CONSTRAINT workoutlogs_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- PostgreSQL database dump complete
--

