--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.0
-- Dumped by pg_dump version 9.3.0
-- Started on 2014-04-24 09:41:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 174 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1959 (class 0 OID 0)
-- Dependencies: 174
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 37146)
-- Name: channel; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE channel (
    id integer NOT NULL,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.channel OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 37177)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 37154)
-- Name: program; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE program (
    id integer NOT NULL,
    name character varying(255),
    program_type character varying(255)
);


ALTER TABLE public.program OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 37162)
-- Name: schedule; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE schedule (
    id integer NOT NULL,
    length bigint NOT NULL,
    starttime bigint NOT NULL,
    channel_id integer,
    program_id integer
);


ALTER TABLE public.schedule OWNER TO postgres;

--
-- TOC entry 1948 (class 0 OID 37146)
-- Dependencies: 170
-- Data for Name: channel; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO channel VALUES (8, 'fox', 'Fox');
INSERT INTO channel VALUES (9, 'finance', 'CNBC');
INSERT INTO channel VALUES (10, 'For Kids', 'Cartoon network');
INSERT INTO channel VALUES (2, 'asd', 'asd');


--
-- TOC entry 1960 (class 0 OID 0)
-- Dependencies: 173
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 7, true);


--
-- TOC entry 1949 (class 0 OID 37154)
-- Dependencies: 171
-- Data for Name: program; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO program VALUES (1, 'Charlie Rose', 'News');
INSERT INTO program VALUES (2, 'Matrix', 'Movie');
INSERT INTO program VALUES (3, 'Lione king', 'Kids');
INSERT INTO program VALUES (4, 'Seven News', 'News');
INSERT INTO program VALUES (5, 'Tom and Jerry', 'Kids');
INSERT INTO program VALUES (6, 'StarWars', 'Movie');
INSERT INTO program VALUES (7, 'House of cards', 'Movie');


--
-- TOC entry 1950 (class 0 OID 37162)
-- Dependencies: 172
-- Data for Name: schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO schedule VALUES (20, 900000, 1388531720188, 10, 3);
INSERT INTO schedule VALUES (21, 900000, 1388618125660, 10, 5);
INSERT INTO schedule VALUES (22, 900000, 1388704535054, 10, 3);
INSERT INTO schedule VALUES (23, 900000, 1388790947670, 10, 3);
INSERT INTO schedule VALUES (24, 900000, 1388877358661, 10, 6);
INSERT INTO schedule VALUES (25, 900000, 1388531711336, 8, 1);
INSERT INTO schedule VALUES (26, 900000, 1388618117018, 8, 4);
INSERT INTO schedule VALUES (27, 900000, 1388790922813, 8, 7);
INSERT INTO schedule VALUES (28, 900000, 1388532627376, 8, 1);
INSERT INTO schedule VALUES (29, 900000, 1388964637900, 8, 6);
INSERT INTO schedule VALUES (30, 900000, 1388531754074, 9, 4);
INSERT INTO schedule VALUES (31, 900000, 1388963759702, 9, 7);
INSERT INTO schedule VALUES (32, 900000, 1388790904291, 9, 4);
INSERT INTO schedule VALUES (33, 900000, 1389050111674, 9, 2);
INSERT INTO schedule VALUES (34, 900000, 1388618115772, 9, 2);
INSERT INTO schedule VALUES (35, 900000, 1388533530936, 9, 2);
INSERT INTO schedule VALUES (3, 900000, 1388531703195, 2, 1);
INSERT INTO schedule VALUES (5, 900000, 1388532635530, 2, 1);
INSERT INTO schedule VALUES (6, 900000, 1388533540404, 2, 1);
INSERT INTO schedule VALUES (7, 900000, 1388534442396, 2, 1);


--
-- TOC entry 1834 (class 2606 OID 37153)
-- Name: channel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY channel
    ADD CONSTRAINT channel_pkey PRIMARY KEY (id);


--
-- TOC entry 1836 (class 2606 OID 37161)
-- Name: program_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY program
    ADD CONSTRAINT program_pkey PRIMARY KEY (id);


--
-- TOC entry 1838 (class 2606 OID 37166)
-- Name: schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);


--
-- TOC entry 1839 (class 2606 OID 37167)
-- Name: fk_3hivp9yuw5lj2tw94flb5ok6f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT fk_3hivp9yuw5lj2tw94flb5ok6f FOREIGN KEY (channel_id) REFERENCES channel(id);


--
-- TOC entry 1840 (class 2606 OID 37172)
-- Name: fk_ofbe9nh7r6f1twui1pb7y5fh4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT fk_ofbe9nh7r6f1twui1pb7y5fh4 FOREIGN KEY (program_id) REFERENCES program(id);


--
-- TOC entry 1958 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-04-24 09:41:06

--
-- PostgreSQL database dump complete
--

