def search_similar(question_embedding):

    vector_string = "[" + ",".join(
        map(str, question_embedding)
    ) + "]"

    conn = get_connection()

    cursor = conn.cursor()

    cursor.execute(
        """
        SELECT FILE_NAME,
               CONTENT
        FROM DOCUMENT_STORE
        ORDER BY VECTOR_DISTANCE(
              EMBEDDING,
              TO_VECTOR(:1),
              COSINE
        )
        FETCH FIRST 5 ROWS ONLY
        """,
        [vector_string]
    )

    results = cursor.fetchall()

    cursor.close()
    conn.close()

    return results