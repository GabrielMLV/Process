
CREATE TABLE IF NOT EXISTS process
(
    id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 00000001 MINVALUE 00000001 MAXVALUE 9223372036854775807 CACHE 1 ),
    name_requester character varying(70) NOT NULL,
    zip_code character varying(10) NOT NULL,
    city character varying(255) NOT NULL,
    district character varying(255) NOT NULL,
    street character varying(255) NOT NULL,
    num_house character varying(10),
    date_process timestamp without time zone NOT NULL,
    filename_process character varying(255),
    deleted BOOLEAN DEFAULT FALSE,

    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT process_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS files
(
    id character varying(255) NOT NULL,
    data oid,
    file_name character varying(255),
    file_type character varying(255),
    CONSTRAINT files_pkey PRIMARY KEY (id)
)
