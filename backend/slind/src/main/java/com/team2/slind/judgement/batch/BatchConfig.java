package com.team2.slind.judgement.batch;


//@Configuration
//@RequiredArgsConstructor
//@Slf4j
public class BatchConfig {
//    private Logger logger = LoggerFactory.getLogger(BatchConfig.class);
//    private final int CHUNK_SIZE = 3;
//    private final JudgementMapper judgementMapper;
//    private final ArticleMapper articleMapper;
//    private final BoardMapper boardMapper;
//    private final PlatformTransactionManager transactionManager;
//    @Override
//    protected PlatformTransactionManager getTransactionManager() {
//        return transactionManager;
//    }
//    @Bean
//    public JobRepository createJobRepository() throws Exception {
//        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setTransactionManager(transactionManager);
//        factoryBean.setIsolationLevelForCreate("ISOLATION_DEFAULT");
//
//        factoryBean.setIncrementerFactory(new org.springframework.batch.item.database.support.DefaultDataFieldMaxValueIncrementerFactory(dataSource));
//
//        JobRepository jobRepository = factoryBean.getObject();
//        return jobRepository;
//
//    }

//    @Bean
//    public Job judgementJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("judgementCleanupJob", jobRepository)
//                .start(judgementStep(jobRepository, transactionManager))
//                .build();
//    }

//    @Bean
//    public Step judgementStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("judgementCleanupStep", jobRepository)
//                .<Judgement, Judgement>chunk(CHUNK_SIZE, transactionManager)
//                .reader(judgementReader())
//                .processor(judgementProcessor())
//                .writer(judgementWriter())
//                .build();
//    }
//    @Bean
//    public ItemReader<Judgement> judgementReader() {
//        return new ItemReader<>() {
//            private List<Judgement> judgements = null;
//            private int nextIndex = 0;
//
////            @Override
////            public Judgement read() {
////                if (judgements == null) {
////                    // Load judgements using MyBatis Mapper
////                    LocalDateTime createdDttm = LocalDateTime.now().minusMinutes(1);
////                    judgements = judgementMapper.findByStatus();
////                    logger.info("Loaded {} judgements with status = 'ON'", judgements.size());
////                    System.out.println("Loaded judgement with status ON : "+ judgements.size());
////                }
////
////                if (nextIndex < judgements.size()) {
////                    return judgements.get(nextIndex++);
////                } else {
////                    return null; // No more items
////                }
////            }
//        };
//    }


//    @Bean
//    public ItemProcessor<Judgement, Judgement> judgementProcessor() {
//        return judgement -> {
//            logger.info("Jugement in Progressor : {}", judgement.getJudgementPk());
//            logger.info("Jugement in Progressor Filtering : {}", judgement.getJudgementPk());
//            System.out.println("Judgement Pk : "+judgement.getJudgementPk());
//            System.out.println("Judgement Like_count : "+judgement.getLikeCount());
//            System.out.println("Judgement dislike_count : "+judgement.getDislikeCount());
//
//
//            // 상태 처리 로직 추가
//            return judgement;
//        };
//    }

//    @Bean
//    public ItemWriter<Judgement> judgementWriter() {
//        long time = System.currentTimeMillis();
//        System.out.println("시작 : " + time);
//
//        return items -> {
//            for (Judgement judgement : items) {
//                int likeCount = judgement.getLikeCount();
//                int dislikeCount = judgement.getDislikeCount();
//                int totalVotes = likeCount + dislikeCount;
//<<<<<<< Updated upstream
//                if (totalVotes >= 1000 && likeCount >= dislikeCount*2) {
//=======
//                if (totalVotes >= 6 && likeCount >= dislikeCount*2) {
//>>>>>>> Stashed changes
//                    logger.info("Judgement {} marked as 승소", judgement.getJudgementPk());
//                    judgementMapper.finishJudgementWin(judgement.getJudgementPk());
//                    if (judgement.getArticlePk() != null) {
//                        // Article 삭제
//                        articleMapper.deleteArticle(judgement.getArticlePk());
//                        logger.info("Deleted Article: {}", judgement.getArticlePk());
//                        System.out.println("승소");
//                    } else if (judgement.getBoardPk() != null) {
//                        // Board 삭제
//
//                        boardMapper.deleteByBoardPk(judgement.getBoardPk());
//                        logger.info("Deleted Board: {}", judgement.getBoardPk());
//                    }
//                }else{
//                    judgementMapper.finishJudgementLose(judgement.getJudgementPk());
//                    System.out.println("패소");
//                }
//                logger.info("Finished Judgement : {}", judgement.getJudgementPk());
//            }
//            long endTime = System.currentTimeMillis();
//            System.out.println("끝 : " + endTime);
//
//            System.out.println("return 전 : " +( endTime-time));
//        };
//    }
}
