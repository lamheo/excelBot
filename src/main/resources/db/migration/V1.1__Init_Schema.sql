CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence START WITH 1 INCREMENT BY 1;

DROP TABLE IF EXISTS excel_bot.user, excel_bot.request, excel_bot.response;

CREATE TABLE excel_bot.user
(
    id         BIGINT       GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(255) NOT NULL,
    isDeleted  BOOLEAN      NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    updated_by VARCHAR(255) NOT NULL,
    deleted    BOOLEAN NULL,
    deleted_at TIMESTAMP NULL,
    deleted_by VARCHAR(255) NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE excel_bot.request
(
    id          BIGINT       GENERATED ALWAYS AS IDENTITY,
    user_id     BIGINT       NOT NULL,
    is_excel    BOOLEAN      NOT NULL,
    is_generate BOOLEAN      NOT NULL,
    request     VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    updated_by VARCHAR(255) NOT NULL,
    deleted    BOOLEAN NULL,
    deleted_at TIMESTAMP NULL,
    deleted_by VARCHAR(255) NULL,
    CONSTRAINT request_pkey PRIMARY KEY (id)
);

CREATE TABLE excel_bot.response
(
    id          BIGINT       GENERATED ALWAYS AS IDENTITY,
    request_id  BIGINT       NOT NULL,
    result      VARCHAR(255)      NOT NULL,
    response    VARCHAR(255)      NOT NULL,
    execution_time  BIGINT NOT NULL,
    vote BIGINT NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    updated_by VARCHAR(255) NOT NULL,
    deleted    BOOLEAN NULL,
    deleted_at TIMESTAMP NULL,
    deleted_by VARCHAR(255) NULL,
    CONSTRAINT response_pkey PRIMARY KEY (id)
);
